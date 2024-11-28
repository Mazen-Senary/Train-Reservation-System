package com.example.trainreservation;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    Button loginButton;
@FXML
Button signup;
@FXML
Button Admin;
    @FXML
    private Label messageLabel;
@FXML
private Label clockLabel;
    private DB database = new DB();//object from class DB


     ;
    @FXML
    private void LoginButtonAction(Event e) {// method el bta5od el username w password 3lshan validtion w 3lshan tn2l lel home page b3d el validation
        String username = usernameField.getText();// geeb el etkatb fl text field w e3mlo store fl username
        String password = passwordField.getText();//zy el ablha bs bt3ml store fl password
        if (database.validateLogin(username, password)) {// object database calls validation method
            store.setUsername(username); // stores username in the setter od user in class  store


            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));// lw validation sa7 byn2l 3al homepage
                Parent root = loader.load();
                // Continue with scene transition
                Node node = (Node) e.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("HomePage");
                stage.show();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            messageLabel.setText("Invalid credentials");// ghlat el validation ro7 l lable etb3 el text
        }
    }



    public void profileClick1(Event e) {// bywady 3al signup page
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();//crea
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Signup");
            stage.show();//displays the page on screen
        } catch (IOException exception) {

        }
    }
    public void AdminCLick(Event e) {// bywady 3al signup page
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();//crea
            Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin Login");
            stage.show();//displays the page on screen
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
                    Platform.runLater(() -> clockLabel.setText(currentTime));
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Preserve interrupt status
                }
            }
        });
    }

}






