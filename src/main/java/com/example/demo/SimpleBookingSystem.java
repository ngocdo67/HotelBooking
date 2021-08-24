package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleBookingSystem implements BookingSystem{
    private final Room[] rooms;
    private int closestRoom;
    private int roomsPerFloor;
    private int floor;

    public SimpleBookingSystem (int floor, int roomsPerFloor) {
        this.floor = floor;
        this.roomsPerFloor = roomsPerFloor;
        this.rooms = SimpleBookingSystemUtilities.populateRooms(floor, roomsPerFloor);
        closestRoom = 0;
    }

    @Override
    public Optional<String> checkInRoom() {
        if (closestRoom >= rooms.length) {
            return Optional.empty();
        }
        Room selectedRoom = rooms[closestRoom];
        selectedRoom.setStatus(Status.OCCUPIED);
        while (closestRoom < floor * roomsPerFloor) {
            if (rooms[closestRoom].getStatus() == Status.AVAILABLE) {
                break;
            } else {
                closestRoom++;
            }
        }
        return Optional.of(selectedRoom.getId());
    }

    @Override
    public void checkOutRoom(String roomId) throws UnsupportedOperationException{
        changeRoomStatus(roomId, Status.OCCUPIED, Status.VACANT, "Can only check out occupied rooms.");
    }

    @Override
    public void markRoomAvailable(String roomId) {
        int distance = changeRoomStatus(roomId, Status.VACANT, Status.AVAILABLE, "Can only clean vacant rooms.");
        if (distance < closestRoom) {
            this.closestRoom = distance;
        }
    }

    @Override
    public void markRoomRepair(String roomId) {
    }

    @Override
    public List<String> getAllAvailableRooms() {
        List<String> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getStatus() == Status.AVAILABLE) {
                availableRooms.add(room.getId());
            }
        }
        return availableRooms;
    }

    private int changeRoomStatus (String roomId, Status oldStatus, Status newStatus, String errorMessage) {
        int distance = SimpleBookingSystemUtilities.getDistance(roomId, roomsPerFloor);
        Room requestedRoom = rooms[distance];
        if (requestedRoom.getStatus() == oldStatus) {
            requestedRoom.setStatus(newStatus);
        } else {
            throw new UnsupportedOperationException(errorMessage);
        }
        return distance;
    }

}
