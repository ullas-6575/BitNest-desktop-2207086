package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CheckoutController {

    @FXML private GridPane guestsGrid;
    @FXML private Label lblStatus;

    @FXML
    public void initialize() {
        loadGuests();
    }

    private void loadGuests() {
        guestsGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);

        List<Map<String, String>> bookings = DatabaseHandler.getAllBookings();

        if (bookings.isEmpty()) {
            lblStatus.setText("No guests currently checked in.");
            return;
        } else {
            lblStatus.setText("");
        }

        int row = 1;
        for (Map<String, String> booking : bookings) {
            String roomNum = booking.get("roomNum");
            String guestName = booking.get("name");


            Label lblRoom = new Label(roomNum);
            lblRoom.setStyle("-fx-font-size: 14px;");


            Label lblName = new Label(guestName);

            Button btnCheckout = new Button("Checkout");
            btnCheckout.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-cursor: hand;");


            btnCheckout.setOnAction(e -> handleCheckoutAction(roomNum));
            guestsGrid.add(lblRoom, 0, row);
            guestsGrid.add(lblName, 1, row);
            guestsGrid.add(btnCheckout, 2, row);

            row++;
        }
    }

    private void handleCheckoutAction(String roomNumber) {
        boolean success = DatabaseHandler.checkOutGuest(roomNumber);
        if (success) {
            lblStatus.setText("Checkout successful for Room " + roomNumber);
            lblStatus.setTextFill(Color.GREEN);
            loadGuests();
        } else {
            lblStatus.setText("Error performing checkout.");
            lblStatus.setTextFill(Color.RED);
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminpage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}