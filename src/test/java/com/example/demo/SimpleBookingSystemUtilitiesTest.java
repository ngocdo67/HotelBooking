package com.example.demo;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SimpleBookingSystemUtilitiesTest {

    @Test
    public void testPopulateRooms() {
        Room[] expected = new Room[] {
                new Room("1A"), new Room("1B"), new Room("1C"), new Room("1D"), new Room("1E"),
                new Room("2E"), new Room("2D"), new Room("2C"), new Room("2B"), new Room("2A"),
                new Room("3A"), new Room("3B"), new Room("3C"), new Room("3D"), new Room("3E"),
                new Room("4E"), new Room("4D"), new Room("4C"), new Room("4B"), new Room("4A"),
        };
        assertEquals(SimpleBookingSystemUtilities.populateRooms(4, 5), expected);
    }
}