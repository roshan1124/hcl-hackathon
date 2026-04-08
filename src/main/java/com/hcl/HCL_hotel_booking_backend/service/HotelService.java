package com.hcl.HCL_hotel_booking_backend.service;

import com.hcl.HCL_hotel_booking_backend.entity.Hotel;
import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    Hotel getHotelById(Long id);

    List<Hotel> getAllHotels();

    Hotel updateHotel(Long id, Hotel hotel);

    void deleteHotel(Long id);

    List<Hotel> getHotelsByLocation(String location);

    List<Hotel> getActiveHotels();
}