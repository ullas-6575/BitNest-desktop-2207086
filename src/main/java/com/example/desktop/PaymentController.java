package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PaymentController {

    @FXML
    private Label lblTotalBill;

    private double amount;
    private String guestName;
    private String guestPhone;
    private String guestNid;
    private String checkIn;
    private String checkOut;
    private List<String> roomNumbers;

    public void setPaymentData(double bill, String name, String phone, String nid, String in, String out, List<String> rooms) {
        this.amount = bill;
        this.guestName = name;
        this.guestPhone = phone;
        this.guestNid = nid;
        this.checkIn = in;
        this.checkOut = out;
        this.roomNumbers = rooms;

        lblTotalBill.setText(" "+amount);
    }

    @FXML
    void handleConfirmPayment(ActionEvent event) {
        boolean success = DatabaseHandler.saveBooking(
                guestName, guestPhone, guestNid, checkIn, checkOut, roomNumbers
        );

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Payment Successful! Booking Confirmed.");
            goHome(event);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Payment processed but Database Save Failed.");
        }
    }

    private void goHome(ActionEvent event) {
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

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}