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

public class VIPcontroller implements BackSwitch{
    @FXML
    Button vipbtn1, vipbtn2, vipbtn3,back7;
    @FXML
    Label clocklabel;

    public void profileClick(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Checkout.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Checkout");
            stage.show();
        } catch (IOException exception) {

        }

    }
    public void profile(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.show();
        } catch (IOException exception) {

        }

    }
    public void support(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Supprt.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Support");
            stage.show();
        } catch (IOException exception) {

        }

    }
    public void schedule(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Schedule.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Schedule");
            stage.show();
        } catch (IOException exception) {

        }

    }

    public void profileClick2(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Checkout.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Checkout");
            stage.show();
        } catch (IOException exception) {

        }

    }
    public void profileClick3(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Checkout.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Checkout");
            stage.show();
        } catch (IOException exception) {

        }

    }
    @Override
    public void onBackClick(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("HomePage");
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
