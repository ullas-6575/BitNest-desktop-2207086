module com.example.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.desktop to javafx.fxml;
    exports com.example.desktop;
}