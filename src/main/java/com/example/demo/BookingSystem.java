package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface BookingSystem {
    Optional<String> assignRoom ();
    void checkOutRoom (String roomId);
    void markRoomAvailable (String roomId);
    void markRoomRepair (String roomId);
    List<String> getAllAvailableRooms ();
}
