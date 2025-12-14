package com.example.desktop;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class adminController {

    @FXML
    void handleSeeGuests(ActionEvent event) {

        System.out.println("Loading Guest List...");
    }

    @FXML
    void handleModify(ActionEvent event) {

        System.out.println("Opening Modify Screen...");
    }

    @FXML
    void handleCheckout(ActionEvent event) {

        System.out.println("Proceeding to Checkout...");
    }
    @FXML
    void handleSearch(ActionEvent event) {

        String input = "Room 101";
        System.out.println("Searching for: " + input);


    }
}