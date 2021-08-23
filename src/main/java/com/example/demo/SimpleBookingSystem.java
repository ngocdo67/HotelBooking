package com.example.demo;

import java.util.List;
import java.util.Optional;

public class SimpleBookingSystem implements BookingSystem{
    private int floor;
    private int roomsPerFloor;
    private Room[] rooms;

    public SimpleBookingSystem (int floor, int roomsPerFloor) {
        this.floor = floor;
        this.roomsPerFloor = roomsPerFloor;

    }

    @Override
    public Optional<String> assignRoom() {
        return Optional.empty();
    }

    @Override
    public void checkOutRoom(String roomId) {

    }

    @Override
    public void markRoomAvailable(String roomId) {

    }

    @Override
    public void markRoomRepair(String roomId) {

    }

    @Override
    public List<String> getAllAvailableRooms() {
        return null;
    }


}
