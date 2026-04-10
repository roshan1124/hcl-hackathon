package com.hcl.HCL_hotel_booking_backend.service;

import com.hcl.HCL_hotel_booking_backend.dto.AuthRequest;
import com.hcl.HCL_hotel_booking_backend.dto.AuthResponse;
import com.hcl.HCL_hotel_booking_backend.dto.RegisterRequest;
import com.hcl.HCL_hotel_booking_backend.dto.UserDTO;

public interface AuthService {

    AuthResponse login(AuthRequest authRequest);

    UserDTO register(RegisterRequest registerRequest);

    UserDTO getCurrentUser(String email);

    void logout(String token);
}