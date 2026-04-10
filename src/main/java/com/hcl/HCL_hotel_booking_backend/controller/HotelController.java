package com.hcl.HCL_hotel_booking_backend.controller;

import com.hcl.HCL_hotel_booking_backend.entity.Hotel;
import com.hcl.HCL_hotel_booking_backend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Create Hotel - Admin only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.createHotel(hotel);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    // Get all hotels - Public
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    // Get hotel by ID - Public
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hotel);
    }

    // Update hotel - Admin only
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        if (updatedHotel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedHotel);
    }

    // Delete hotel - Admin only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    // Get hotels by location - Public
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Hotel>> getHotelsByLocation(@PathVariable String location) {
        return ResponseEntity.ok(hotelService.getHotelsByLocation(location));
    }

    // Get active hotels - Public
    @GetMapping("/active")
    public ResponseEntity<List<Hotel>> getActiveHotels() {
        return ResponseEntity.ok(hotelService.getActiveHotels());
    }
}