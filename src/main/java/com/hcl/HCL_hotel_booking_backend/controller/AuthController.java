package com.hcl.HCL_hotel_booking_backend.controller;

import com.hcl.HCL_hotel_booking_backend.dto.AuthRequest;
import com.hcl.HCL_hotel_booking_backend.dto.AuthResponse;
import com.hcl.HCL_hotel_booking_backend.dto.RegisterRequest;
import com.hcl.HCL_hotel_booking_backend.dto.UserDTO;
import com.hcl.HCL_hotel_booking_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest registerRequest) {
        UserDTO registeredUser = authService.register(registerRequest);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.login(authRequest);
        return ResponseEntity.ok(response);
    }

    // Get current user profile
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDTO user = authService.getCurrentUser(email);
        return ResponseEntity.ok(user);
    }

    // Logout user
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            authService.logout(token);
        }
        return ResponseEntity.ok("Logged out successfully");
    }
}