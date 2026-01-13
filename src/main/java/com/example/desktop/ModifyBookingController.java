package com.example.desktop;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ModifyBookingController {

    @FXML private ComboBox<String> cbFromRoom;
    @FXML private ComboBox<String> cbToRoom;
    @FXML private Label lblStatus;

    @FXML
    public void initialize() {
        loadData();
    }

    private void loadData() {

        List<String> occupied = DatabaseHandler.getOccupiedRooms();
        cbFromRoom.setItems(FXCollections.observableArrayList(occupied));


        List<String> allRooms = DatabaseHandler.getAllRoomNumbers();
        cbToRoom.setItems(FXCollections.observableArrayList(allRooms));
    }

    @FXML
    void handleMove(ActionEvent event) {
        String fromRoom = cbFromRoom.getValue();
        String toRoom = cbToRoom.getValue();

        if (fromRoom == null || toRoom == null) {
            lblStatus.setText("Please select both rooms.");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }

        if (fromRoom.equals(toRoom)) {
            lblStatus.setText("Source and Target rooms cannot be the same.");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }

        if (cbFromRoom.getItems().contains(toRoom)) {
            lblStatus.setText("Error: Room " + toRoom + " is already occupied!");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean success = DatabaseHandler.moveGuest(fromRoom, toRoom);

        if (success) {
            lblStatus.setText("Success! Guest moved to Room " + toRoom);
            lblStatus.setStyle("-fx-text-fill: green;");
            loadData();
        } else {
            lblStatus.setText("Error updating database.");
            lblStatus.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminpage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}