package com.example.trainreservation;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Checkoutcontroller implements BackSwitch {
    @FXML
    Button masterbtn, paypal,back8;
    @FXML
    Label clocklabel;

    public void profileClick1(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Master.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mastercard");
            stage.show();
        } catch (IOException exception) {

        }
    }
    public void profileClick2(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Paypal.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("PayPal");
            stage.show();
        } catch (IOException exception) {

        }
    }
    @Override
    public void onBackClick(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("VIP.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("VIP");
            stage.show();
        } catch (IOException exception) {

        }
    }
    @FXML
    private void initialize() {
        startClock();  // Start the clock when the controller is initialized
    }
    private final ExecutorService clockExecutor = Executors.newSingleThreadExecutor();
    private void startClock() {
        clockExecutor.submit(() -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String currentTime = LocalTime.now().format(formatter);
                    Platform.runLater(() -> clocklabel.setText(currentTime));
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Preserve interrupt status
                }
            }
        });
    }
}


