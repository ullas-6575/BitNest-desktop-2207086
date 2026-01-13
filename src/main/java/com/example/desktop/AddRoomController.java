package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddRoomController {

    @FXML private TextField txtRoomNumber;
    @FXML private TextField txtType;
    @FXML private TextField txtPrice;
    @FXML private Label lblStatus;

    @FXML
    void handleSave(ActionEvent event) {
        String room = txtRoomNumber.getText();
        String type = txtType.getText();
        String priceStr = txtPrice.getText();

        if (room.isEmpty() || type.isEmpty() || priceStr.isEmpty()) {
            lblStatus.setText("Please fill all fields!");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            boolean success = DatabaseHandler.addRoom(room, type, price);

            if (success) {
                lblStatus.setText("Room Added Successfully!");
                lblStatus.setStyle("-fx-text-fill: green;");
                txtRoomNumber.clear();
                txtType.clear();
                txtPrice.clear();
            } else {
                lblStatus.setText("Error: Room ID may already exist.");
                lblStatus.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            lblStatus.setText("Price must be a valid number.");
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
            System.out.println("Error: Could not load adminpage.fxml");
        }
    }
}