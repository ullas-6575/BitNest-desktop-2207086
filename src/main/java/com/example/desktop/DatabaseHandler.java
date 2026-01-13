package com.example.desktop;

import com.example.desktop.Room_selection_controller.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {

    private static final String DB_URL = "jdbc:sqlite:bitnest.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void initDB() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String sqlRooms = "CREATE TABLE IF NOT EXISTS rooms (" +
                    "room_number TEXT PRIMARY KEY, " +
                    "type TEXT, " +
                    "price REAL, " +
                    "is_available INTEGER DEFAULT 1)";
            stmt.execute(sqlRooms);

            String sqlBookings = "CREATE TABLE IF NOT EXISTS bookings (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "guest_name TEXT, " +
                    "phone TEXT, " +
                    "check_in TEXT, " +
                    "check_out TEXT, " +
                    "room_number TEXT, " +
                    "FOREIGN KEY(room_number) REFERENCES rooms(room_number))";
            stmt.execute(sqlBookings);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addRoom(String roomNumber, String type, double price) {
        String sql = "INSERT INTO rooms(room_number, type, price, is_available) VALUES(?, ?, ?, 1)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomNumber);
            pstmt.setString(2, type);
            pstmt.setDouble(3, price);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT room_number, type, price, is_available FROM rooms";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String num = rs.getString("room_number");
                double price = rs.getDouble("price");
                int available = rs.getInt("is_available");
                String status = (available == 1) ? "Available" : "Booked";

                list.add(new Room(num, status, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Room> getAvailableRoomsByType(String type) {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT room_number, type, price, is_available FROM rooms WHERE type = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String num = rs.getString("room_number");
                double price = rs.getDouble("price");
                int available = rs.getInt("is_available");
                String status = (available == 1) ? "Available" : "Booked";
                list.add(new Room(num, status, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean bookRoom(String name, String phone, String checkIn, String checkOut, String roomNumber) {
        String insertBooking = "INSERT INTO bookings(guest_name, phone, check_in, check_out, room_number) VALUES(?, ?, ?, ?, ?)";
        String updateRoom = "UPDATE rooms SET is_available = 0 WHERE room_number = ?";

        try (Connection conn = connect()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstBooking = conn.prepareStatement(insertBooking);
                 PreparedStatement pstRoom = conn.prepareStatement(updateRoom)) {

                pstBooking.setString(1, name);
                pstBooking.setString(2, phone);
                pstBooking.setString(3, checkIn);
                pstBooking.setString(4, checkOut);
                pstBooking.setString(5, roomNumber);
                pstBooking.executeUpdate();

                pstRoom.setString(1, roomNumber);
                pstRoom.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Map<String, String>> getAllBookings() {
        List<Map<String, String>> bookingList = new ArrayList<>();
        String sql = "SELECT * FROM bookings ORDER BY room_number ASC";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, String> booking = new HashMap<>();
                booking.put("id", String.valueOf(rs.getInt("id")));
                booking.put("name", rs.getString("guest_name"));
                booking.put("phone", rs.getString("phone"));
                booking.put("checkIn", rs.getString("check_in"));
                booking.put("checkOut", rs.getString("check_out"));
                booking.put("roomNum", rs.getString("room_number"));
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    public static void checkOut(int bookingId, String roomNumber) {
        String deleteBooking = "DELETE FROM bookings WHERE id = ?";
        String freeRoom = "UPDATE rooms SET is_available = 1 WHERE room_number = ?";
        try (Connection conn = connect()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstDel = conn.prepareStatement(deleteBooking);
                 PreparedStatement pstFree = conn.prepareStatement(freeRoom)) {
                pstDel.setInt(1, bookingId);
                pstDel.executeUpdate();
                pstFree.setString(1, roomNumber);
                pstFree.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getAllRoomNumbers() {
        List<String> rooms = new ArrayList<>();
        String sql = "SELECT room_number FROM rooms ORDER BY room_number ASC";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rooms.add(rs.getString("room_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }


    public static Map<String, String> getGuestByRoom(String roomNumber) {
        Map<String, String> details = new HashMap<>();
        String sql = "SELECT * FROM bookings WHERE room_number = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                details.put("found", "true");
                details.put("name", rs.getString("guest_name"));
                details.put("phone", rs.getString("phone"));
                details.put("checkIn", rs.getString("check_in"));
                details.put("checkOut", rs.getString("check_out"));
            } else {
                details.put("found", "false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    public static List<String> getOccupiedRooms() {
        List<String> rooms = new ArrayList<>();
        String sql = "SELECT room_number FROM bookings ORDER BY room_number ASC";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rooms.add(rs.getString("room_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public static boolean moveGuest(String currentRoom, String newRoom) {
        String sql = "UPDATE bookings SET room_number = ? WHERE room_number = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newRoom);
            pstmt.setString(2, currentRoom);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean checkOutGuest(String roomNumber) {
        String deleteBooking = "DELETE FROM bookings WHERE room_number = ?";
        String updateRoom = "UPDATE rooms SET is_available = 1 WHERE room_number = ?";

        try (Connection conn = connect()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstDel = conn.prepareStatement(deleteBooking);
                 PreparedStatement pstUpd = conn.prepareStatement(updateRoom)) {


                pstDel.setString(1, roomNumber);
                pstDel.executeUpdate();


                pstUpd.setString(1, roomNumber);
                pstUpd.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}