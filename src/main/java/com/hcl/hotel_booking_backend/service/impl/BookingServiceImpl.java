package com.hcl.hotel_booking_backend.service.impl;

import com.hcl.hotel_booking_backend.entity.Booking;
import com.hcl.hotel_booking_backend.repository.BookingRepository;
import com.hcl.hotel_booking_backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        if (!isRoomAvailable(booking.getRoomId(), booking.getCheckIn(), booking.getCheckOut())) {
            return null;
        }

        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public List<Booking> getBookingsByRoom(Long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    @Override
    public Booking updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus(status);
            return bookingRepository.save(booking);
        }
        return null;
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);
        }
    }

    @Override
    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        return !bookingRepository.existsByRoomIdAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(
                roomId, checkOut, checkIn);
    }
}