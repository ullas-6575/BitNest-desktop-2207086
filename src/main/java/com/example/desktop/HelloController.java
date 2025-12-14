package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnAdminLogin;

    @FXML
    private Button btnBookRoom;
    @FXML
    void handleAdminLogin(ActionEvent event) {

    }

    @FXML
    void handleBookRoom(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        btnBookRoom.setOnAction(this::handleBookRoom);
        btnAdminLogin.setOnAction(this::handleAdminLogin);
    }
}
