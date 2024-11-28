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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MasterContoller implements BackSwitch {
    @FXML
    Button back10,paybtn;// button names
    @FXML
    TextField card_name, cvvId, card_number,exp_date ; //textfield names

    @FXML
    Label errorid;//label
    @FXML
    Label clocklabel;//clock



    public void AddMasterCard(ActionEvent e ) {// by3ml insert fl database
        String Holder_name = card_name.getText();//store le bytktb fl variables
        String Card_number = card_number.getText();
        String CVV = cvvId.getText();
        String EXP_date = exp_date.getText();


        DB db = new DB();//object mn database


        if (Holder_name.isEmpty() || Card_number.isEmpty() || CVV.isEmpty() || EXP_date.isEmpty()) {// message bttl3 lw dost pay w sebt el fields fadya
            errorid.setText("Please fill all fields to continue.");
        } else {
            boolean success = db.insertMasterCard(Holder_name, Card_number, CVV, EXP_date);//calls insertMastercard from db class
            if (success) {

                try {
                    Node node = (Node) e.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("ThankYou.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("ThankYou");
                    stage.show();
                } catch (IOException exception) {

                }
            } else {

                errorid.setText("Please enter valid information");
            }
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
    public void onBackClick(Event e) { //backtn override 3la el interface
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
