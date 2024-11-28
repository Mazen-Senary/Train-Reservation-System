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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduleController implements BackSwitch {

    @FXML
    private Label clockLabel;  // Label to display the current time
    @FXML
    private Label trainidLabel;
    @FXML
    private Label datalabel;
    @FXML
    private Label noofseats;
    @FXML
    private Label trainmodelLabel;
    @FXML
    private Label DEPLabel;
    @FXML
    private Label ARRLabel;
    @FXML
     Button viewid;
    @FXML
    private Label trainidLabel1;
    @FXML
    private Label datalabel1;
    @FXML
    private Label noofseats1;
    @FXML
    private Label trainmodelLabel1;
    @FXML
    private Label DEPLabel1;
    @FXML
    private Label ARRLabel1;
    @FXML
    private TextField depid;
    @FXML
    private TextField arrid;
    @FXML
    private Label error;



    private ExecutorService executorService = Executors.newCachedThreadPool();
    private final ExecutorService clockExecutor = Executors.newSingleThreadExecutor();
    private DB db = new DB();

    @Override
    public void onBackClick(Event e) {
        try {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("HomePage");
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        startClock();  // Start the clock when the controller is initialized
    }

    @FXML
    private void viewButtonAction(ActionEvent event) {
        String depStation = depid.getText(); // get el text me el text field el user hyktb feha 3shan ycmpare between user info and database
        String arrStation = arrid.getText();
        if (depStation == null || depStation.isEmpty() || arrStation == null || arrStation.isEmpty()) {
            Platform.runLater(() -> { // single threaded runs method later one at a time execute 3la 7asb el mwgod thread monafsl
                error.setText("Please fill all the fields to display the trains.");
                clearLabels();// deletes labels inside tickets el ta7t
            });
        } else {
            executorService.submit(() -> {//giving a note to someone
                boolean trainAvailable = displayTrainInfo(depStation, arrStation);//variable type boolean
                if (trainAvailable) {
                    Platform.runLater(() -> error.setText("")); // Clear error if train available
                } else {
                    Platform.runLater(() -> {
                        error.setText("No available trains right now.");
                        clearLabels(); // Clear labels if no trains are available // deletes labels inside tickets el ta7t
                    });
                }
            });
        }
    }

    private boolean displayTrainInfo(String depStation, String arrStation) {
        ResultSet resultSet = db.displayTrains(depStation, arrStation);
        try { //hydisplay el train info el gya mel datbase 3la hasb el dep_station w el arr_station
            if (resultSet.next()) { //result set stores el gy mn execute query el fi method display trains w bt3mlo store fi el strings el ta7t
                String trainId = resultSet.getString("Train_ID");
                String trainModel = resultSet.getString("Train_model");
                String date = resultSet.getString("Date");
                String noOfSeats = resultSet.getString("no_of_seats");
                String deptStation = resultSet.getString("dept_station");
                String arrivalStation = resultSet.getString("arrival_station");

                Platform.runLater(() -> {
                    trainidLabel.setText(trainId);
                    trainmodelLabel.setText(trainModel);
                    datalabel.setText(date);
                    noofseats.setText(noOfSeats);
                    DEPLabel.setText(deptStation);
                    ARRLabel.setText(arrivalStation);

                    trainidLabel1.setText(trainId);
                    trainmodelLabel1.setText(trainModel);
                    datalabel1.setText(date);
                    noofseats1.setText(noOfSeats);
                    DEPLabel1.setText(deptStation);
                    ARRLabel1.setText(arrivalStation);
                });
                return true;
            } else {
                Platform.runLater(this::clearLabels);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void clearLabels() { // hyclear el labels
        trainidLabel.setText("");
        trainmodelLabel.setText("");
        datalabel.setText("");
        noofseats.setText("");
        DEPLabel.setText("");
        ARRLabel.setText("");

        trainidLabel1.setText("");
        trainmodelLabel1.setText("");
        datalabel1.setText("");
        noofseats1.setText("");
        DEPLabel1.setText("");
        ARRLabel1.setText("");
    }

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

    public void checkOUT(Event e) {
        String depStation = depid.getText();
        String arrStation = arrid.getText();
        try { // hena msh hy3ml book 2la lw hwa mkhtar el depstation wle arrstation
            if(depStation == null || depStation.isEmpty() || arrStation == null || arrStation.isEmpty()){
                error.setText("Please fill all the fields to display the trains and book your ticket.");
        }
        else{
                Node node = (Node) e.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Checkout.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }

        catch (IOException exception) {
            exception.printStackTrace();
        }

    }


    public void VIP(Event e) {         String depStation = depid.getText();
        String arrStation = arrid.getText();
        try {//my h3ml load ll(VIP.fxml) 8er lma yb2a hatt dep-station wel ARR_station
            if(depStation == null || depStation.isEmpty() || arrStation == null || arrStation.isEmpty()){
                error.setText("Please fill all the fields to display the trains and book your ticket.");
            }
            else{
                Node node = (Node) e.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("VIP.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }

        catch (IOException exception) {
            exception.printStackTrace();
        }
        }
    }

