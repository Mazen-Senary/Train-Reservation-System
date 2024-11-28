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

public class HomePageController implements BackSwitch {
    @FXML
    Button Pbtn, Sbtn, Bbtn, Subtn, vipbtn; //access el buttons
@FXML
Label clocklabel;//label el clock


    public void profileClick(Event e) {// button el nywady 3la profile page
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

    @Override
    public void onBackClick(Event e) {//backbutton gy mn el interface
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException exception) {

        }
    }
    public void profileClick3(Event e) {//button el byn2l lel vip page
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




    public void profileClick4(Event e) {// byn2l 3la support page
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Supprt.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("support");
            stage.show();
        } catch (IOException exception) {

        }
    }


    public void profileClick5(Event e) {// byn2l 3la schedule page
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
}

