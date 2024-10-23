package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jwt.JwtTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.*;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jwt.JwtAuthFilter;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getUserByName(@RequestParam String name) {
        Optional<User> existingUser = userRepository.findByUsername(name);
        if (existingUser.isPresent()) {
            return ResponseEntity.ok(Map.of("success", 1, "user", existingUser.get()));
        }
        return ResponseEntity.ok(Map.of("success", 0, "message", "User \"" + name + "\" not found. "));
    }
    
    @PostMapping("/user_register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user) {
        // Check if the username or password is empty or omitted
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success", 0, "error", "Username is required."));
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success", 0, "error", "Password is required."));
        }
        
        try {
            // Check if a user with the required username exists
            Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
            if (existingUser.isPresent()) {
                return ResponseEntity.ok(Map.of("success", 0, "error", "User already exists."));
            }

	        // Encode the password using BCrypt
	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
	    	
	    	// Set default role (guest)
	    	user.setRole(roleRepository.findById(1).get());
	
	        // Save the user to the database
	        User savedUser = userRepository.save(user);
	        
            // Build success response
            return ResponseEntity.ok(Map.of("success", 1, "id", savedUser.getId()));
        } catch (Exception e) {
            // Build error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", 0, "error", e.getMessage()));
        }
    }
    
	@PostMapping("/user_login")
    public ResponseEntity<Map<String, Object>> loginUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        // Check if the username or password is empty or omitted
        if (user.getUsername() == null || user.getUsername().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", 0, "error", "Username and password are required."));
        }

        try {
	        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
	        Authentication authentication = authenticationManager.authenticate(authenticationToken);
	        
	        if (authentication.isAuthenticated()) {
	        	Optional<User> savedUser = userRepository.findByUsername(user.getUsername());
	        	int savedUserId = savedUser.get().getId();
		        
	        	//Add Jwt token to cookie (so that front-end devs can omit the process of adding headers)
		        String token = jwtTokenUtil.generateToken(user.getUsername());
		        Cookie cookie = new Cookie(JwtAuthFilter.tokenTag, token);
		        cookie.setPath("/");
		        cookie.setMaxAge(JwtTokenUtil.EXPIRATION_TIME);
		        response.addCookie(cookie);
		        
	        	return ResponseEntity.ok(Map.of("success", 1, "id", savedUserId, "roles", savedUser.get().getRole().getName()));
	        } else {
	        	return ResponseEntity.ok(Map.of("success", 0, "error", "Invalid username or password."));
	        }
        } catch (Exception e) {
            // Build error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", 0, "error", e.getMessage()));
        }
    }

	@GetMapping("/user_logout")
	public ResponseEntity<Map<String, Object>> logoutUser(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
		try {
        	Optional<User> user = userRepository.findByUsername(auth.getName());
        	if (!user.isPresent()) throw new Exception("Database discrepency detected");
        	Integer userid = user.get().getId();

        	//Because Jwt token is stateless, just tell browser to delete cookie
	        Cookie cookie = new Cookie(JwtAuthFilter.tokenTag, "");
	        cookie.setPath("/");
	        cookie.setMaxAge(0);
	        response.addCookie(cookie);

        	return ResponseEntity.ok(Map.of("success", 1, "id", userid));
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", 0, "error", e.getMessage()));
		}
	}
}
