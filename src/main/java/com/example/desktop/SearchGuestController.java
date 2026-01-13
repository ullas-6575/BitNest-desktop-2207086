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
import java.util.Map;

public class SearchGuestController {

    @FXML private ComboBox<String> roomComboBox;
    @FXML private Label lblName;
    @FXML private Label lblPhone;
    @FXML private Label lblCheckIn;
    @FXML private Label lblCheckOut;
    @FXML private Label lblStatus;

    @FXML
    public void initialize() {
        List<String> rooms = DatabaseHandler.getAllRoomNumbers();
        roomComboBox.setItems(FXCollections.observableArrayList(rooms));
    }

    @FXML
    void handleSearch(ActionEvent event) {
        String selectedRoom = roomComboBox.getValue();

        if (selectedRoom == null) {
            lblStatus.setText("Please select a room first.");
            lblStatus.setStyle("-fx-text-fill: red;");
            clearLabels();
            return;
        }

        Map<String, String> data = DatabaseHandler.getGuestByRoom(selectedRoom);

        if ("true".equals(data.get("found"))) {
            lblName.setText(data.get("name"));
            lblPhone.setText(data.get("phone"));
            lblCheckIn.setText(data.get("checkIn"));
            lblCheckOut.setText(data.get("checkOut"));

            lblStatus.setText("Guest Found!");
            lblStatus.setStyle("-fx-text-fill: green;");
        } else {
            clearLabels();
            lblStatus.setText("Room " + selectedRoom + " is currently empty.");
            lblStatus.setStyle("-fx-text-fill: orange;");
        }
    }

    private void clearLabels() {
        lblName.setText("-");
        lblPhone.setText("-");
        lblCheckIn.setText("-");
        lblCheckOut.setText("-");
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