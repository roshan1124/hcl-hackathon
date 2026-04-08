package com.hcl.hotel_booking_backend.repository;

import com.hcl.hotel_booking_backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByRoomId(Long roomId);

    List<Booking> findByStatus(String status);

    boolean existsByRoomIdAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(
            Long roomId, LocalDate checkOut, LocalDate checkIn);
}