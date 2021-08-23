package com.example.demo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void testCheckOutRoom() {
    }

    @Test
    public void testMarkRoomAvailable() {
    }

    @Test
    public void testMarkRoomRepair() {
    }

    @Test
    public void testGetAllAvailableRooms() {
    }
}