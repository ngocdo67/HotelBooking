package com.example.demo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.text.html.Option;
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
    public void shouldCheckInFirstRoom() {
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1A"));
        assertFalse(bookingSystem.getAllAvailableRooms().contains("1A"));
    }

    @Test
    public void shouldCheckInSecondRoom() {
        bookingSystem.checkInRoom();
        assertEquals(bookingSystem.checkInRoom(),Optional.of("1B"));
        assertFalse(bookingSystem.getAllAvailableRooms().contains("1B"));
    }

    @Test
    public void shouldCheckInLastRoom() {
        for (int i=0; i < 19; i++) {
            bookingSystem.checkInRoom();
        }
        assertEquals(bookingSystem.checkInRoom(), Optional.of("4A"));
        assertFalse(bookingSystem.getAllAvailableRooms().contains("4A"));
    }

    @Test
    public void shouldNotCheckInRoomWhenAllRoomsAreOccupied() {
        for (int i=0; i < 20; i++) {
            bookingSystem.checkInRoom();
        }
        assertTrue(bookingSystem.checkInRoom().isEmpty());
    }

    @Test
    public void shouldCheckOutOccupiedRoom() {
        bookingSystem.checkInRoom();
        assertFalse(bookingSystem.getAllAvailableRooms().contains("1A"));
        bookingSystem.checkOutRoom("1A");
        bookingSystem.markRoomRepair("1A");
        bookingSystem.markRoomAvailable("1A");
        assertTrue(bookingSystem.getAllAvailableRooms().contains("1A"));
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void shouldNotCheckOutAvailableRoom() {
        bookingSystem.checkOutRoom("4A");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void shouldNotCheckOutVacantRoom() {
        bookingSystem.checkInRoom();
        bookingSystem.checkOutRoom("1A");
        bookingSystem.checkOutRoom("1A");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void shouldNotCheckOutRepairRoom() {
        bookingSystem.checkInRoom();
        bookingSystem.checkOutRoom("1A");
        bookingSystem.markRoomRepair("1A");
        bookingSystem.checkOutRoom("1A");
    }

    @Test
    public void shouldMarkVacantRoomAsAvailable() {
        assertTrue(bookingSystem.getAllAvailableRooms().contains("1A"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1A"));
        bookingSystem.checkOutRoom("1A");
        bookingSystem.markRoomAvailable("1A");
        assertTrue(bookingSystem.getAllAvailableRooms().contains("1A"));
    }

    @Test (expectedExceptions = UnsupportedOperationException.class)
    public void shouldNotMarkOccupiedRoomAsAvailable() {
        assertTrue(bookingSystem.getAllAvailableRooms().contains("1A"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1A"));
        bookingSystem.markRoomAvailable("1A");
    }

    @Test (expectedExceptions = UnsupportedOperationException.class)
    public void shouldNotMarkAvailableRoomAsAvailable() {
        assertTrue(bookingSystem.getAllAvailableRooms().contains("1A"));
        bookingSystem.markRoomAvailable("1A");
    }

    @Test
    public void shouldRefreshClosestRoom () {
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1A"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1B"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1C"));
        bookingSystem.checkOutRoom("1A");
        bookingSystem.markRoomAvailable("1A");
        bookingSystem.checkOutRoom("1C");
        bookingSystem.markRoomAvailable("1C");
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1A"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1C"));
    }

    @Test
    public void shouldRefreshClosestRoomOnNextFloor () {
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1A"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1B"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1C"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1D"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1E"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("2E"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("2D"));
        bookingSystem.checkOutRoom("1A");
        bookingSystem.markRoomAvailable("1A");
        bookingSystem.checkOutRoom("2D");
        bookingSystem.markRoomAvailable("2D");
        assertEquals(bookingSystem.checkInRoom(), Optional.of("1A"));
        assertEquals(bookingSystem.checkInRoom(), Optional.of("2D"));
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