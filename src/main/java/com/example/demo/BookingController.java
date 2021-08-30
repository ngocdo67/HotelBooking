package com.example.demo;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingProcessor bookingProcessor;

    @PostMapping("/checkin")
    public @ResponseBody ResponseEntity<String> checkInRoom() throws NotFoundException{
        Optional<String> bookedRoom = bookingProcessor.checkInRoom();
        if (bookedRoom.isPresent()) {
            return ResponseEntity.ok(bookedRoom.get());
        } else {
            throw new NotFoundException("No room checked in.");
        }

    }

    @PostMapping("/checkout")
    public @ResponseBody ResponseEntity<Void> checkOutRoom(@RequestBody RoomRequest request) {
        bookingProcessor.checkOutRoom(request.getRoomId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/mark-available")
    public @ResponseBody ResponseEntity<Void> markRoomAvailable (@RequestBody RoomRequest request) {
        bookingProcessor.markRoomAvailable(request.getRoomId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/mark-repair")
    public @ResponseBody ResponseEntity<Void> markRoomRepair (@RequestBody RoomRequest request) {
        bookingProcessor.markRoomRepair(request.getRoomId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/finish-repair")
    public @ResponseBody ResponseEntity<Void> finishRoomRepair (@RequestBody RoomRequest request) {
        bookingProcessor.finishRoomRepair(request.getRoomId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/available-rooms")
    public @ResponseBody ResponseEntity<List<String>> getAllAvailableRooms() {
        return ResponseEntity.ok(bookingProcessor.getAllAvailableRooms());
    }
}
