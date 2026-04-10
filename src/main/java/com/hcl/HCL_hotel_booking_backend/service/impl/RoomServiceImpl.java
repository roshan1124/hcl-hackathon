package com.hcl.hotel_booking_backend.service.impl;

import com.hcl.hotel_booking_backend.entity.Room;
import com.hcl.hotel_booking_backend.repository.RoomRepository;
import com.hcl.hotel_booking_backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getRoomsByHotel(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }

    @Override
    public Room updateRoom(Long id, Room roomDetails) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            room.setHotelId(roomDetails.getHotelId());
            room.setType(roomDetails.getType());
            room.setPrice(roomDetails.getPrice());
            return roomRepository.save(room);
        }
        return null;
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> getRoomsByType(String type) {
        return roomRepository.findByType(type);
    }
}