package com.hcl.HCL_hotel_booking_backend.service;

import com.hcl.HCL_hotel_booking_backend.dto.RoomResponse;
import com.hcl.HCL_hotel_booking_backend.enitty.Room;
import com.hcl.HCL_hotel_booking_backend.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public Optional<RoomResponse> getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .map(this::toResponse);

    }

    private RoomResponse toResponse(Room room) {
        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setHotel_id(room.getHotel_id());
        response.setType(room.getType());
        response.setPrice(room.getPrice());
        return response;
    }


}
