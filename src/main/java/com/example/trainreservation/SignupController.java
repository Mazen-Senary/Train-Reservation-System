package com.example.trainreservation;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SignupController implements BackSwitch {


    @FXML
    Button back1;

    @FXML
    Button createid;

    @FXML
    TextField usersignup, SSNid, DOBid, emailid, pnid;

    @FXML
    PasswordField passid;

    @FXML
    Label errorLabel;
    @FXML
     Label clocklabel;


    public void createUser(ActionEvent e )  {
        String username = usersignup.getText();
        String password = passid.getText();
        String email = emailid.getText();
        String phoneNumber = pnid.getText();
        String user_ssn = SSNid.getText();
        String age = DOBid.getText();
// by equal el strings ll labels
        DB db = new DB();// once you create an object from class db connection is valid in this class
        boolean success = db.Insert(username, password, email, phoneNumber, user_ssn, age);

        if (success) {

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
        } else {
            // There was an error adding the user
            errorLabel.setText("Username is already taken");

        }
    }

    @Override
    public void onBackClick(Event e) {
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
