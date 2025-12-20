package com.example.desktop;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class adminController {

    @FXML
    void handleSeeGuests(ActionEvent event) {

        System.out.println("Loading Guest List...");
    }

    @FXML
    void handleModify(ActionEvent event) {

        System.out.println("Opening Modify Screen...");
    }

    @FXML
    void handleCheckout(ActionEvent event) {

        System.out.println("Proceeding to Checkout...");
    }
    @FXML
    void handleSearch(ActionEvent event) {

        String input = "Room 101";
        System.out.println("Searching for: " + input);


    }
    @FXML
    void handleBackAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Could not load admin-login.fxml to go back.");
        }
    }
}