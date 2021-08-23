package com.example.demo;

import java.util.List;
import java.util.Optional;

public class SimpleBookingSystem implements BookingSystem{
    private final Room[] rooms;
    private int closestRoom;

    public SimpleBookingSystem (int floor, int roomsPerFloor) {
        this.rooms = SimpleBookingSystemUtilities.populateRooms(floor, roomsPerFloor);
        closestRoom = 0;
    }

    @Override
    public Optional<String> assignRoom() {
        if (closestRoom >= rooms.length) {
            return Optional.empty();
        }
        Room selectedRoom = rooms[closestRoom++];
        return Optional.of(selectedRoom.getId());
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
