package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.StoreOccupancy;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa.StoreOccupancyRepository;

import java.util.*;

@RestController
public class StoreOccupancyController {

    @Autowired
    private StoreOccupancyRepository storeOccupancyRepository;

    @GetMapping("/stor_occup")
    public Optional<StoreOccupancy> getStoreOccupancy(@RequestParam(required = false) Integer id) {
        if (id != null) {
        	return storeOccupancyRepository.findById(id);
        }
        return storeOccupancyRepository.findTopByOrderByIdDesc();
    }

    @PostMapping("/stor_occup_report")
    public ResponseEntity<Map<String, Object>> addStoreOccupancy(Authentication auth, @RequestParam StoreOccupancy stor_occup) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Create a new record
        	stor_occup.setId(0);
        	stor_occup.setTimeStamp(null);
            StoreOccupancy saved_occup = storeOccupancyRepository.save(stor_occup);
            response.put("success", 1);
            response.put("id", saved_occup.getId());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("success", 0);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
