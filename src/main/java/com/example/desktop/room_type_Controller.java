package com.example.desktop;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class room_type_Controller {

    @FXML
    void handleSingleRoom(ActionEvent event) {
       // System.out.println("Single Room Selected");
        goToRoomSelection(event, "Single Room", 700.0);
    }

    @FXML
    void handleApartment(ActionEvent event) {
       // System.out.println("Apartment Selected");
        goToRoomSelection(event, "Apartment", 1500.0);
    }

    private void goToRoomSelection(ActionEvent event, String type, double price) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("room_selection.fxml"));
            Parent root = loader.load();
            Room_selection_controller controller = loader.getController();
            controller.setBookingData(type, price);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("Error loading room_selection.fxml");
        }
    }

    @FXML
    void handleBackAction(ActionEvent event) {
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