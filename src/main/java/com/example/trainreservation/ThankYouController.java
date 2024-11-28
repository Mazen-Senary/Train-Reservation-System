package com.example.trainreservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class ThankYouController {

    @FXML
    private Hyperlink homeHyperlink;

    @FXML
    private void initialize() {
        // Initialization logic if needed
    }

    @FXML
    private void goToHomePage(ActionEvent event) {
        try {
            // Load the home page FXML
            Parent homePage = FXMLLoader.load(getClass().getResource("/com/example/trainreservation/homePage.fxml"));

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            stage.setScene(new Scene(homePage));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
