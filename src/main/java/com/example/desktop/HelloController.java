package com.example.desktop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnBookRoom;

    @FXML
    private Button btnAdminLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnBookRoom.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("room-selection.fxml"));
                Parent root = loader.load();

                Room_selection_controller controller = loader.getController();
                controller.loadAllRooms();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnAdminLogin.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-login.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}