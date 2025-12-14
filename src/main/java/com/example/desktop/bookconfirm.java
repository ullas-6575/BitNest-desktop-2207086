package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jdk.internal.icu.impl.CharacterIteratorWrapper;

import java.time.LocalDate;

public class bookconfirm {

    @FXML
    private TextField nameField;
    @FXML
    private DatePicker checkInDate;


    @FXML
    void handleConfirm(ActionEvent event) {

        String name = nameField.getText();
        CharacterIteratorWrapper idField = null;
        String id = idField.getText();


        LocalDate inDate = checkInDate.getValue();
//        LocalDate outDate = checkOutDate.getValue();
//        if(inDate == null || outDate == null || name.isEmpty()) {
//            System.out.println("Please fill all fields");
//            return;
//        }
        System.out.println("Booking confirmed for: " + name);
//        System.out.println("From: " + inDate + " To: " + outDate);
    }
}
