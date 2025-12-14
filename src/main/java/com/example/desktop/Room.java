package com.example.desktop;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

public class Room {
    private final String roomNumber;
    private final String floor;
    private final String price;
    private final String status;

    public Room(String roomNumber, String floor, String price, String status) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.price = price;
        this.status = status;
    }

    @FXML
    private GridPane roomsGrid;

    public void loadRooms(List<Room> rooms) {
        int row = 1;
        for (Room room : rooms) {

            Label lblNum = new Label(room.getRoomNumber());
            Label lblFloor = new Label(room.getFloor());
            Label lblStatus = new Label(room.getStatus());
            CheckBox chkSelect = new CheckBox();


            roomsGrid.add(lblNum, 0, row);
            roomsGrid.add(lblFloor, 1, row);
            roomsGrid.add(lblStatus, 2, row);
            roomsGrid.add(chkSelect, 3, row);

            row++;
        }
    }
    public String getRoomNumber() { return roomNumber; }
    public String getFloor() { return floor; }
    public String getPrice() { return price; }
    public String getStatus() { return status; }
}
