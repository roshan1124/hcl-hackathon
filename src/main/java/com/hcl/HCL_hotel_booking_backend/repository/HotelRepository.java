package com.hcl.HCL_hotel_booking_backend.repository;

import com.hcl.HCL_hotel_booking_backend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByLocation(String location);

    List<Hotel> findByStarRating(Integer starRating);

    List<Hotel> findByIsActiveTrue();

    List<Hotel> findByLocationAndIsActiveTrue(String location);
}