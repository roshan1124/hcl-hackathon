package com.hcl.HCL_hotel_booking_backend.controller;


import com.hcl.HCL_hotel_booking_backend.dto.RoomResponse;
import com.hcl.HCL_hotel_booking_backend.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    //    @GetMapping("/all")
//    public ResponseEntity<RoomResponse> getAllRooms() {
//        RoomResponse response = roomService.getAllRooms();
//        return ResponseEntity.ok(response);
//    }
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long roomId) {
        RoomResponse response = roomService.getRoomById(roomId).get();
        return ResponseEntity.ok(response);
    }
}

