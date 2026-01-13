package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class adminController {

    @FXML
    void handleBackAction(ActionEvent event) {
        switchScene(event, "hello-view.fxml");
    }

    @FXML
    void handleSeeGuests(ActionEvent event) {
        switchScene(event, "guest-list.fxml");
    }


    @FXML
    void handleAddRoom(ActionEvent event) {

        switchScene(event, "add-room.fxml");
    }

    @FXML
    void handleSearch(ActionEvent event) {

        switchScene(event, "search-guest.fxml");
    }

    @FXML
    void handleModify(ActionEvent event) {
        switchScene(event, "modify-booking.fxml");
    }
    @FXML
    void handleCheckout(ActionEvent event) {
        switchScene(event, "checkout.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading: " + fxmlFile);
        }
    }
}