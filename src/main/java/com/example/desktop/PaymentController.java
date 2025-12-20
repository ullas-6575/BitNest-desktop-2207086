package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentController {

    @FXML private Label lblTotalBill;
    @FXML private TextField cardNoField;
    @FXML private TextField cardHolderField;
    @FXML private TextField expiryMonthField;
    @FXML private TextField expiryYearField;

    private double totalAmount;

    public void setBillData(double amount) {
        this.totalAmount = amount;
        lblTotalBill.setText(String.format("%.2f", totalAmount));
    }

    @FXML
    void handleConfirmPayment(ActionEvent event) {
        String cardNo = cardNoField.getText();

        if (cardNo.isEmpty()) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("payment_success.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}