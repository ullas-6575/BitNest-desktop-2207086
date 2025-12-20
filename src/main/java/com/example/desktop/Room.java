package com.example.desktop;

public class Room {
    private final String roomNumber;
    private final String status;

    public Room(String roomNumber, String status) {
        this.roomNumber = roomNumber;
        this.status = status;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getStatus() { return status; }
}