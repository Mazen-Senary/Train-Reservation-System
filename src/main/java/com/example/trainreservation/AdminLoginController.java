package com.example.trainreservation;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminLoginController {
    @FXML
    Button back2;
    @FXML
    Button loginadmin;
    @FXML
    TextField textid;
    @FXML
    PasswordField pass2;
    public void profileClick1(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();//crea
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Signup");
            stage.show();//displays the page on screen
        } catch (IOException exception) {

        }

    }

    public void profileClick2(Event e) {// bywady 3al signup page
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();//crea
            Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("AdminHome");
            stage.show();//displays the page on screen
        } catch (IOException exception) {

        }
    }

}
