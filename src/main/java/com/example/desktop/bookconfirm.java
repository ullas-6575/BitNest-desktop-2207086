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
import java.time.temporal.ChronoUnit;

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
    private double dailyPrice;

    public void setRoomData(String roomNumber, double price) {
        this.selectedRoomNumber = roomNumber;
        this.dailyPrice = price;
    }

    @FXML
    void handleConfirm(ActionEvent event) {
        if (checkInDate.getValue() == null || checkOutDate.getValue() == null) {
            return;
        }

        long days = ChronoUnit.DAYS.between(checkInDate.getValue(), checkOutDate.getValue());
        if (days <= 0) days = 1;

        double totalBill = days * dailyPrice;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
            Parent root = loader.load();

            PaymentController controller = loader.getController();
            controller.setBillData(totalBill);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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