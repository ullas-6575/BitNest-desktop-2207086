package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Room_selection_controller {

    @FXML
    private GridPane roomsGrid;

    @FXML
    private Button btnBook;


    private String currentRoomType;
    private double currentPrice;

    private List<RoomCheckBox> checkBoxes = new ArrayList<>();

    private static class RoomCheckBox {
        CheckBox checkBox;
        String roomNumber;

        public RoomCheckBox(CheckBox checkBox, String roomNumber) {
            this.checkBox = checkBox;
            this.roomNumber = roomNumber;
        }
    }

    public void setBookingData(String roomType, double price) {
        this.currentRoomType = roomType;
        this.currentPrice = price;

        System.out.println("Loading rooms for: " + roomType);

        List<Room> rooms = generateDummyRooms(roomType);
        loadRoomsToGrid(rooms);
    }


    private List<Room> generateDummyRooms(String type) {
        List<Room> list = new ArrayList<>();
        if (type.contains("Single")) {
            list.add(new Room("101", "Available"));
            list.add(new Room("102", "Booked"));
            list.add(new Room("103", "Available"));
        } else {
            list.add(new Room("201", "Available"));
            list.add(new Room("202", "Available"));
        }
        return list;
    }

    private void loadRoomsToGrid(List<Room> rooms) {
        roomsGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);
        checkBoxes.clear();

        int row = 1;
        for (Room room : rooms) {
            Label lblNum = new Label(room.getRoomNumber());
            Label lblStatus = new Label(room.getStatus());
            CheckBox chkSelect = new CheckBox();

            if ("Booked".equalsIgnoreCase(room.getStatus())) {
                lblStatus.setTextFill(Color.RED);
                chkSelect.setDisable(true);
            } else {
                lblStatus.setTextFill(Color.GREEN);
            }

            roomsGrid.add(lblNum, 0, row);
            roomsGrid.add(lblStatus, 1, row);
            roomsGrid.add(chkSelect, 2, row);
            checkBoxes.add(new RoomCheckBox(chkSelect, room.getRoomNumber()));
            row++;
        }
    }

    @FXML
    void handleBookSelected(ActionEvent event) {
        String selectedRoom = null;

        for (RoomCheckBox rcb : checkBoxes) {
            if (rcb.checkBox.isSelected()) {
                selectedRoom = rcb.roomNumber;
                break;
            }
        }

        if (selectedRoom != null) {
//            System.out.println("Booking Room: " + selectedRoom);
            goToGuestDetails(event, selectedRoom);
        } else {
//            System.out.println("Please select a room.");
        }
    }

    private void goToGuestDetails(ActionEvent event, String roomNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmbooking.fxml"));
            Parent root = loader.load();
            bookconfirm controller = loader.getController();
            controller.setRoomData(roomNumber);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("Error loading confirmbooking.fxml");
        }
    }
    @FXML
    void handleBackAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("room_type.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}