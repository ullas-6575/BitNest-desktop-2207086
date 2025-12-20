package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnAdminLogin;
    @FXML
    private Button btnBookRoom;

    @FXML
    void handleAdminLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Could not load admin-login.fxml. Check filename.");
        }
    }

    @FXML
    void handleBookRoom(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("room_type.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Could not load room_type.fxml.");
        }
    }
    @FXML
    public void initialize() {

        btnBookRoom.setOnAction(this::handleBookRoom);
        btnAdminLogin.setOnAction(this::handleAdminLogin);
    }
}