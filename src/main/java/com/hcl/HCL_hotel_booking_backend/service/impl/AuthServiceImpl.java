package com.hcl.HCL_hotel_booking_backend.service.impl;

import com.hcl.HCL_hotel_booking_backend.dto.AuthRequest;
import com.hcl.HCL_hotel_booking_backend.dto.AuthResponse;
import com.hcl.HCL_hotel_booking_backend.dto.RegisterRequest;
import com.hcl.HCL_hotel_booking_backend.dto.UserDTO;
import com.hcl.HCL_hotel_booking_backend.entity.User;
import com.hcl.HCL_hotel_booking_backend.enums.Role;
import com.hcl.HCL_hotel_booking_backend.repository.UserRepository;
import com.hcl.HCL_hotel_booking_backend.service.AuthService;
import com.hcl.HCL_hotel_booking_backend.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        // Find user by email
        User user = userRepository.findByEmail(authRequest.getEmail()).orElse(null);

        // Check if user exists and password matches
        if (user == null || !passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            AuthResponse errorResponse = new AuthResponse();
            errorResponse.setToken(null);
            errorResponse.setEmail(null);
            errorResponse.setFullName(null);
            errorResponse.setRole(null);
            return errorResponse;
        }

        // Check if user is enabled
        if (!user.isEnabled()) {
            AuthResponse errorResponse = new AuthResponse();
            errorResponse.setToken(null);
            errorResponse.setEmail(null);
            errorResponse.setFullName(null);
            errorResponse.setRole(null);
            return errorResponse;
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        // Create response
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setRole(user.getRole().name());

        return response;
    }

    @Override
    public UserDTO register(RegisterRequest registerRequest) {
        // Check if email already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return null;
        }

        // Check if phone number already exists
        if (registerRequest.getPhoneNumber() != null &&
                userRepository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {
            return null;
        }

        // Create new user
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRole(Role.USER);
        user.setEnabled(true);

        // Save to database
        User savedUser = userRepository.save(user);

        // Convert to DTO
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO getCurrentUser(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public void logout(String token) {
        // Logout handled on client side
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole().name());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}