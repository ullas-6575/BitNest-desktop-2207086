package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private List<RoomCheckBox> checkBoxes = new ArrayList<>();


    private static class RoomCheckBox {
        CheckBox checkBox;
        String roomNumber;
        double price;

        public RoomCheckBox(CheckBox checkBox, String roomNumber, double price) {
            this.checkBox = checkBox;
            this.roomNumber = roomNumber;
            this.price = price;
        }
    }


    public static class Room {
        private String roomNumber;
        private String status;
        private double price;

        public Room(String roomNumber, String status, double price) {
            this.roomNumber = roomNumber;
            this.status = status;
            this.price = price;
        }
        public String getRoomNumber() { return roomNumber; }
        public String getStatus() { return status; }
        public double getPrice() { return price; }
    }

    public void loadAllRooms() {
        List<Room> rooms = DatabaseHandler.getAllRooms();
        loadRoomsToGrid(rooms);
    }

    private void loadRoomsToGrid(List<Room> rooms) {
        roomsGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);
        checkBoxes.clear();

        if (rooms.isEmpty()) {
            Label emptyLbl = new Label("No rooms found.");
            emptyLbl.setTextFill(Color.RED);
            roomsGrid.add(emptyLbl, 0, 1);
            return;
        }

        int row = 1;
        for (Room room : rooms) {
            Label lblNum = new Label(room.getRoomNumber() + " (tk " + room.getPrice() + ")");
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

            checkBoxes.add(new RoomCheckBox(chkSelect, room.getRoomNumber(), room.getPrice()));
            row++;
        }
    }

    @FXML
    void handleBookSelected(ActionEvent event) {
        List<String> selectedRooms = new ArrayList<>();
        double totalDailyPrice = 0.0;


        for (RoomCheckBox rcb : checkBoxes) {
            if (rcb.checkBox.isSelected()) {
                selectedRooms.add(rcb.roomNumber);
                totalDailyPrice += rcb.price;
            }
        }

        if (!selectedRooms.isEmpty()) {
            goToGuestDetails(event, selectedRooms, totalDailyPrice);
        } else {
            System.out.println("Please select at least one room.");
        }
    }

    private void goToGuestDetails(ActionEvent event, List<String> roomNumbers, double totalDailyPrice) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmbooking.fxml"));
            Parent root = loader.load();


            bookconfirm controller = loader.getController();
            controller.setBookingData(roomNumbers, totalDailyPrice);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}