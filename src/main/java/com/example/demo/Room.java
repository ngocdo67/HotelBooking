package com.example.demo;

import java.util.Objects;

public class Room {
    private String id;
    private Status status = Status.AVAILABLE;

    public Room(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) && status == room.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
