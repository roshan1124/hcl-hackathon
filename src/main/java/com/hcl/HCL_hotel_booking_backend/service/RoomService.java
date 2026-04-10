package com.hcl.hotel_booking_backend.service;

import com.hcl.hotel_booking_backend.entity.Room;
import java.util.List;

public interface RoomService {

    Room createRoom(Room room);

    Room getRoomById(Long id);

    List<Room> getAllRooms();

    List<Room> getRoomsByHotel(Long hotelId);

    Room updateRoom(Long id, Room room);

    void deleteRoom(Long id);

    List<Room> getRoomsByType(String type);
}