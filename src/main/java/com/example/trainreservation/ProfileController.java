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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProfileController implements BackSwitch {
    @FXML
    Button back8;
    @FXML
    Label usernameLabel,emailLabel,phoneNumberLabel,ageLabel,clocklabel;


    private DB db = new DB();






    public void initialize() {
        // Retrieve username from session and set it in the Profile UI
        String username = store.getUsername();

        if (username != null ) {
            updateProfileLabels(username); // da gy men el store el hwa mb3ot mel loggin store.setusername el fi function LoginButtonAction
        }
        startClock();
    }
    private void updateProfileLabels(String username) {
        ResultSet prof = db.Get_profile(username );
        try { // hyupdate el profile label 3shan lma ylogin yb3t el username wel full info to display fel profile.fxml
            if (prof.next()) {
                usernameLabel.setText(prof.getString("UserName"));
                emailLabel.setText(prof.getString("Email"));
                phoneNumberLabel.setText(prof.getString("Phone_number"));
                ageLabel.setText(prof.getString("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void onBackClick(Event e) {
        //backbtn (interface)
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("HomePage");
            stage.show();
        } catch (IOException exception) {

        }
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

