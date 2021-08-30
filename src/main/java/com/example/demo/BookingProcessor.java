package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookingProcessor {

    private final BookingSystem simpleBookingSystem;

    @Autowired
    public BookingProcessor() {
        simpleBookingSystem = new SimpleBookingSystem();
    }

    public Optional<String> checkInRoom() {
        return simpleBookingSystem.checkInRoom();
    }

    public void checkOutRoom(String roomId) {
        simpleBookingSystem.checkOutRoom(roomId);
    }

    public void markRoomAvailable (String roomId) {
        simpleBookingSystem.markRoomAvailable(roomId);
    }

    public void markRoomRepair (String roomId) {
        simpleBookingSystem.markRoomRepair(roomId);
    }

    public void finishRoomRepair (String roomId) {
        simpleBookingSystem.finishRoomRepair(roomId);
    }

    public List<String> getAllAvailableRooms() {
        return simpleBookingSystem.getAllAvailableRooms();
    }
}

