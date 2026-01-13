package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

    private List<String> selectedRoomNumbers;
    private double totalDailyPrice;

    public void setBookingData(List<String> roomNumbers, double dailyPrice) {
        this.selectedRoomNumbers = roomNumbers;
        this.totalDailyPrice = dailyPrice;
    }

    @FXML
    void handleConfirm(ActionEvent event) {
        if (checkInDate.getValue() == null || checkOutDate.getValue() == null ||
                nameField.getText().isEmpty() || phoneField.getText().isEmpty() || idField.getText().isEmpty()) {
            showAlert("Error", "Please fill all fields.");
            return;
        }

        long days = ChronoUnit.DAYS.between(checkInDate.getValue(), checkOutDate.getValue());
        if (days <= 0) days = 1;

        double finalBill = days * totalDailyPrice;

        goToPayment(event, finalBill,
                nameField.getText(),
                phoneField.getText(),
                idField.getText(),
                checkInDate.getValue().toString(),
                checkOutDate.getValue().toString());
    }

    private void goToPayment(ActionEvent event, double billAmount, String name, String phone, String nid, String inDate, String outDate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
            Parent root = loader.load();

            PaymentController controller = loader.getController();
            controller.setPaymentData(billAmount, name, phone, nid, inDate, outDate, selectedRoomNumbers);

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
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}