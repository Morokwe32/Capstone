package za.ac.cput.controller;

/**
 * UserController.java
 * Controller for User Class
 * Author: Moegamat Isgak Abzal
 * Student Number: 221321810
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder; // Ensure you import PasswordEncoder
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.JwtService; // Ensure you import your JwtService
import za.ac.cput.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    private JwtService jwtService; // Add JwtService for JWT generation

    @Autowired
    private PasswordEncoder passwordEncoder; // Add PasswordEncoder for password matching

    @CrossOrigin(origins = "http://localhost:3315")
    @PostMapping("/register")
    public User register(@RequestBody User registrationRequest) {
        // Extract the registration details from the request object
        String username = registrationRequest.getUserName();
        String password = registrationRequest.getPassword();
        String email = registrationRequest.getEmail();
        System.out.println("Username: " + username);
        System.out.println("This user is now registered");
        return userService.create(registrationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            UserDetails userDetails = userService.loadUserByUsername(username);
            System.out.println("User found: " + userDetails.getUsername());
            System.out.println("Stored password: " + userDetails.getPassword());
            System.out.println("Input password: " + password);

            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                String token = jwtService.generateToken(userDetails.getUsername(), List.of("USER"));
                return ResponseEntity.ok(new LoginResponse(token));
            } else {
                System.out.println("Password mismatch.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (UsernameNotFoundException e) {
            System.out.println("User not found: " + username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @GetMapping("/profile/{userId}")
    public User readUser(@PathVariable Integer userId) {
        System.out.println("/api/admin/users/read was triggered");
        System.out.println("UserService was created...attempting to read user...");
        return userService.read(userId);
    }

    @GetMapping("/{userId}")
    public User read(@PathVariable Integer userId) {
        System.out.println("/api/user/{userId} was triggered");
        return userService.read(userId);
    }
}

class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}