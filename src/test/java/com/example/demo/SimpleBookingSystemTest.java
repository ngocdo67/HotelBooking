package com.example.demo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.testng.Assert.*;

public class SimpleBookingSystemTest {
    private BookingSystem bookingSystem;

    @BeforeMethod
    public void setUp() {
        bookingSystem = new SimpleBookingSystem(4, 5);
    }

    @Test
    public void shouldAssignFirstRoom() {
        assertEquals(bookingSystem.assignRoom(), Optional.of("1A"));
        assertFalse(bookingSystem.getAllAvailableRooms().contains("1A"));
    }

    @Test
    public void shouldAssignSecondRoom() {
        bookingSystem.assignRoom();
        assertEquals(bookingSystem.assignRoom(),Optional.of("1B"));
        assertFalse(bookingSystem.getAllAvailableRooms().contains("1B"));
    }

    @Test
    public void shouldAssignLastRoom() {
        for (int i=0; i < 19; i++) {
            bookingSystem.assignRoom();
        }
        assertEquals(bookingSystem.assignRoom(), Optional.of("4A"));
        assertFalse(bookingSystem.getAllAvailableRooms().contains("4A"));
    }

    @Test
    public void shouldNotAssignRoomWhenAllRoomsAreAssigned() {
        for (int i=0; i < 20; i++) {
            bookingSystem.assignRoom();
        }
        assertTrue(bookingSystem.assignRoom().isEmpty());
    }

    @Test
    public void shouldCheckOutOccupiedRoom() {
        bookingSystem.assignRoom();
        assertFalse(bookingSystem.getAllAvailableRooms().contains("1A"));
        bookingSystem.checkOutRoom("1A");
        assertTrue(bookingSystem.getAllAvailableRooms().contains("1A"));
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void shouldNotCheckOutAvailableRoom() {
        bookingSystem.checkOutRoom("4A");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void shouldNotCheckOutVacantRoom() {
        bookingSystem.assignRoom();
        bookingSystem.checkOutRoom("1A");
        bookingSystem.checkOutRoom("1A");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void shouldNotCheckOutRepairRoom() {
        bookingSystem.assignRoom();
        bookingSystem.checkOutRoom("1A");
        bookingSystem.markRoomRepair("1A");
        bookingSystem.checkOutRoom("1A");
    }

    @Test
    public void testMarkRoomAvailable() {
    }

    @Test
    public void testMarkRoomRepair() {
    }

    @Test
    public void shouldGetAllAvailableRooms() {
        assertEquals(bookingSystem.getAllAvailableRooms(), Arrays.asList(
                "1A", "1B", "1C", "1D", "1E",
                "2E", "2D", "2C", "2B", "2A",
                "3A", "3B", "3C", "3D", "3E",
                "4E", "4D", "4C", "4B", "4A"
        ));
    }
}