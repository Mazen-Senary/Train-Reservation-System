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

public class PaypalController implements BackSwitch {
    @FXML
    Button back9, payid;
    @FXML
    TextField emailid;
    @FXML
    PasswordField passid;
    @FXML
    Label errorid;
    @FXML
    Label clocklabel;




    public void AddPaypal(ActionEvent e ) {
        String Email_or_Mobile_number = emailid.getText();
        String pass = passid.getText();
//by2ra el text fiedls 3shan yinsert fel database

        DB db = new DB();


        if (Email_or_Mobile_number.isEmpty() || pass.isEmpty()) {
            errorid.setText("Please fill all fields to continue."); // lw 3ml pay wmfesh fields hytl3 el error fel errorid
        } else {
            boolean success = db.insertPaypal(Email_or_Mobile_number, pass);
            if (success) {
// w lw success hchange scene llthankyou.fxml
                try {
                    Node node = (Node) e.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("ThankYou.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Thankyou");
                    stage.show();
                } catch (IOException exception) {

                }
            } else {
                // There was an error adding the user
                errorid.setText("Please enter valid information");
            } //lw dakhl haga 8lt zy el cvv aw card number
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





    @Override
    public void onBackClick(Event e) { //back btn (interface)
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Checkout.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {

        }
    }

}
