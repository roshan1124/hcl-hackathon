package com.hcl.HCL_hotel_booking_backend.repository;


import com.hcl.HCL_hotel_booking_backend.enitty.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
