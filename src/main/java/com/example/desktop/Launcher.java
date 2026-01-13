package com.example.desktop;

import javafx.application.Application;
import java.sql.Connection;
import java.sql.Statement;

public class Launcher {
    public static void main(String[] args) {
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt = conn.createStatement()) {

            System.out.println("Updating database schema...");
            stmt.execute("ALTER TABLE rooms ADD COLUMN type TEXT");
            stmt.execute("ALTER TABLE rooms ADD COLUMN price REAL");
            System.out.println("Columns added successfully!");

        } catch (Exception e) {

            System.out.println("Update info: " + e.getMessage());
        }
        DatabaseHandler.initDB();
        Application.launch(HelloApplication.class, args);
    }
}