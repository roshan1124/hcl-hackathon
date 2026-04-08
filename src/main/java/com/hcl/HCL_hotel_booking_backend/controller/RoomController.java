package com.hcl.HCL_hotel_booking_backend.controller;


import com.hcl.HCL_hotel_booking_backend.dto.RoomResponse;
import com.hcl.HCL_hotel_booking_backend.enitty.Room;
import com.hcl.HCL_hotel_booking_backend.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/allRooms/{hotelId}")
    public ResponseEntity<List<RoomResponse>> getAllRooms(@PathVariable String hotelId) {
        List<RoomResponse> responselist = roomService.getAllRooms(hotelId);
        return ResponseEntity.ok(responselist);
    }

    @PostMapping
    public ResponseEntity<String> creatRoom(@RequestBody Room room){
        Room savedRoom = roomService.createRoom(room);
        return ResponseEntity.created(URI.create("/rooms/" + savedRoom.getId())).body("Room added successfully");
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long roomId) {
        RoomResponse response = roomService.getRoomById(roomId).get();
        return ResponseEntity.ok(response);
    }

}

