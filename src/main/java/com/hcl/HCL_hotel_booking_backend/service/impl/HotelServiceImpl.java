package com.hcl.HCL_hotel_booking_backend.service.impl;

import com.hcl.HCL_hotel_booking_backend.entity.Hotel;
import com.hcl.HCL_hotel_booking_backend.repository.HotelRepository;
import com.hcl.HCL_hotel_booking_backend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        hotel.setIsActive(true);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if (hotel == null) {
            return null;
        }

        hotel.setName(hotelDetails.getName());
        hotel.setLocation(hotelDetails.getLocation());
        hotel.setDescription(hotelDetails.getDescription());
        hotel.setStarRating(hotelDetails.getStarRating());
        hotel.setAmenities(hotelDetails.getAmenities());
        hotel.setImageUrl(hotelDetails.getImageUrl());

        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<Hotel> getHotelsByLocation(String location) {
        return hotelRepository.findByLocation(location);
    }

    @Override
    public List<Hotel> getActiveHotels() {
        return hotelRepository.findByIsActiveTrue();
    }
}