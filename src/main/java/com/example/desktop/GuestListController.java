package com.example.desktop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GuestListController {

    @FXML
    private ListView<String> bookingsList;

    @FXML
    public void initialize() {
        if (bookingsList != null) {
            List<Map<String, String>> bookings = DatabaseHandler.getAllBookings();
            ObservableList<String> items = FXCollections.observableArrayList();
            for (Map<String, String> b : bookings) {
                items.add("Room: " + b.get("roomNum") + " | " + b.get("name") + " (" + b.get("phone") + ")");
            }
            bookingsList.setItems(items);
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