package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.*;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.VoucherUser.VoucherStatus;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa.*;

import java.security.SecureRandom;
import java.util.*;

@RestController
public class VoucherController {
	
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

	@Service
	public static class VoucherUserService {

	    @Autowired
	    private VoucherUserRepository voucherUserRepository;

	    public VoucherUser addVoucherToUser(int userid, int voucherid, int days) {
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, days);
	        Date validUntil = cal.getTime();
	        VoucherUser voucherUser = new VoucherUser();
	        voucherUser.setUserid(userid);
	        voucherUser.setVoucherid(voucherid);
	        voucherUser.setCode(generateRandomCode());
	        voucherUser.setStatus(VoucherStatus.READY);
	        voucherUser.setValidUntil(validUntil);
	        return voucherUserRepository.save(voucherUser);
	    }

	    private String generateRandomCode() {
	    	int length = 50;
	        StringBuilder code = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
	            code.append(CHARACTERS.charAt(randomIndex));
	        }
	        return code.toString();
	    }
	}

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherUserRepository voucherUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoucherUserService voucherUserService;

    @GetMapping("/vouchers")
    public ResponseEntity<Map<String, Object>> getAllVouchers(@RequestParam(required = false) Integer userId) {
        try {
	    	if (userId != null) {
	            // Return vouchers of a specific user
	            return ResponseEntity.ok(Map.of("success", 1, "vouchers", voucherUserRepository.findByUserid(userId)));
	        } else {
	            // Return all vouchers
	            return ResponseEntity.ok(Map.of("success", 1, "vouchers", voucherRepository.findAll()));
	        }
        } catch (Exception e) {
            // Build error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", 0, "error", e.getMessage()));
        }
    }

    @PostMapping("/voucher_add")
    public ResponseEntity<Map<String, Object>> addVoucherToUser(Authentication auth, @RequestParam int userId, @RequestParam int voucherId, @RequestParam int days) {
        try {
	    	User user = userRepository.findById(userId).orElse(null);
	        Voucher voucher = voucherRepository.findById(voucherId).orElse(null);
	        if (user == null || voucher == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", 0, "error", "User or voucher not found"));
	        }
	        VoucherUser savedVoucher = voucherUserService.addVoucherToUser(user.getId(), voucher.getId(), days);
	        return ResponseEntity.ok(Map.of("success", 1, "voucher", savedVoucher));
        } catch (Exception e) {
            // Build error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", 0, "error", e.getMessage()));
        }
    }
    
    @DeleteMapping("/voucher_delete")
    public ResponseEntity<Map<String, Object>> deleteVoucher(Authentication auth, @RequestParam int voucherId) {
        try {
	    	Voucher voucher = voucherRepository.findById(voucherId).orElse(null);
	        if (voucher == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", 0, "error", "Voucher not found"));
	        }
	        voucherRepository.delete(voucher);
	        return ResponseEntity.ok(Map.of("success", 1, "id", voucherId));
        } catch (Exception e) {
            // Build error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", 0, "error", e.getMessage()));
        }
    }
}
