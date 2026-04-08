package com.hcl.hotel_booking_backend.service;

import com.hcl.hotel_booking_backend.entity.Booking;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);

    Booking getBookingById(Long id);

    List<Booking> getAllBookings();

    List<Booking> getBookingsByUser(Long userId);

    List<Booking> getBookingsByRoom(Long roomId);

    Booking updateBookingStatus(Long id, String status);

    void cancelBooking(Long id);

    List<Booking> getBookingsByStatus(String status);

    boolean isRoomAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut);
}