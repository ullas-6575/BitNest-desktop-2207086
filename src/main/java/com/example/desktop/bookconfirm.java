package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class bookconfirm {

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField idField;

    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;


    private String selectedRoomNumber;


    public void setRoomData(String roomNumber) {
        this.selectedRoomNumber = roomNumber;
        System.out.println("Initializing booking for Room: " + roomNumber);
    }

    @FXML
    void handleConfirm(ActionEvent event) {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String id = idField.getText();
        LocalDate inDate = checkInDate.getValue();
        LocalDate outDate = checkOutDate.getValue();

        if (name.isEmpty() || phone.isEmpty() || id.isEmpty() || inDate == null || outDate == null) {
//            System.out.println("Error: Please fill in all fields.");
            return;
        }

//        System.out.println("Room: " + selectedRoomNumber);
//        System.out.println("Guest: " + name);
//        System.out.println("Phone: " + phone);
//        System.out.println("Check-in: " + inDate);
//        System.out.println("Check-out: " + outDate);

        // TODO: Save this data to your database here
    }
    @FXML
    void handleBackAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("room_selection.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}