package com.hcl.HCL_hotel_booking_backend.repository;

import com.hcl.HCL_hotel_booking_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (used for login)
    Optional<User> findByEmail(String email);

    // Check if email already exists (used for registration)
    boolean existsByEmail(String email);

    // Check if phone number already exists
    boolean existsByPhoneNumber(String phoneNumber);

    // Find user by email and enabled status
    Optional<User> findByEmailAndEnabled(String email, boolean enabled);
}