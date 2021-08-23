package com.example.demo;

public final class SimpleBookingSystemUtilities {
    private SimpleBookingSystemUtilities() {}

    public static Room[] populateRooms (int floor, int roomsPerFloor) {
        Room[] rooms = new Room[floor * roomsPerFloor];
        for (int distance=0; distance < floor * roomsPerFloor; distance++) {
            int currentFloor = distance / roomsPerFloor + 1;
            char letter;
            int currentRoomPosition;
            if (currentFloor % 2 == 0) {
                currentRoomPosition = roomsPerFloor - 1 - distance % roomsPerFloor;

            } else {
                currentRoomPosition = distance % roomsPerFloor;
            }
            int increment = 0;
            letter = 'A';
            while (increment++ < currentRoomPosition) {
                letter += 1;
            }
            String roomId = Integer.toString(currentFloor) + letter;
            System.out.println (roomId);
            rooms[distance] = new Room(roomId);
        }
        return rooms;
    }
}
