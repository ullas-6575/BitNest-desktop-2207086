package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    @FXML
    void handleLoginAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        lblError.setText("");
        if ("ullas".equals(username) && "12345686".equals(password)) {
            lblError.setStyle("-fx-text-fill: green;");
            lblError.setText("Login Successful!");
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("adminpage.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
//                System.out.println("Error: Could not load adminpage.fxml");
            }

        } else {

            lblError.setStyle("-fx-text-fill: #e74c3c;");
            lblError.setText("Invalid Username or Password.");
        }
    }

    @FXML
    void handleBackAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Could not load hello-view.fxml to go back.");
        }
    }
}