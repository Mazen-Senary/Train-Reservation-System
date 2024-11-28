module com.example.trainreservation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.trainreservation to javafx.fxml;
    exports com.example.trainreservation;
}