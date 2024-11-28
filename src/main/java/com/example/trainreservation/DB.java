package com.example.trainreservation;

import java.sql.*;

public class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/train_system"; // Database link
    private static final String USERNAME = "root"; // Usernameof the Database
    private static final String PASSWORD = ""; // Default password of a Database
    public Connection connection; // Create an object from connection





        public DB() { // constractor
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// selects the suitable driver fr the database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); //print the error
        }
    }
//test m3mola 3lshan n test el connection
    public void testConnection() { // tests connection
        if (connection != null) { // lw el connection sh8al hyrg3 el success
            System.out.println("Connection to the database was successful!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
    public boolean validateLogin(String username, String password) { // validate usernaem and password from the database to check if the user is already have acc or not if he dont have an account error label will pop up "invalid creadantials"
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM userprofile WHERE UserName =? AND Password =?");//
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }//overload
    public boolean Insert(String username, String password, String email) {
        return Insert(username, password, email, "", "", "0");
    }

    public boolean Insert(String username, String password, String email, String phoneNumber, String user_ssn, String age) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM userprofile WHERE UserName = ?");//sql searching in database for the username
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();// bycheck lw el username is already taken wla la
            if(resultSet.next())
            {

                return false;
            }
            statement = connection.prepareStatement("INSERT INTO userprofile (UserName, password, Email, Phone_number, user_ssn, age) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, username);// lw el username msh taken hyinsert all information in the database
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setString(5, user_ssn);
            statement.setString(6, age);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ResultSet Get_profile (String username ){// type result set btrg3 el query
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT UserName , Email , Phone_number , age FROM userprofile WHERE UserName =? ");
            statement.setString(1, username);
//byrg3 info bta3t el username 3shan ttsave fel ProfileController.fxml

            ResultSet result = statement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet displayTrains(String deptStation, String arrStation) {
        try { // bydisplay el trains 3la hasb el dep_station w el arr_station
            PreparedStatement statement = connection.prepareStatement("SELECT Train_ID, Train_model, Date, no_of_seats, dept_station, arrival_station FROM train WHERE dept_station =? AND arrival_station =?");
            statement.setString(1, deptStation);
            statement.setString(2, arrStation);
            ResultSet set = statement.executeQuery();
            return  set;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertMasterCard(String Holder_name, String Card_number, String CVV, String EXP_date) {
        try { // by insert mastercard information fel database
            PreparedStatement statement = connection.prepareStatement("INSERT INTO master_card (Card_holder_name, Card_no,CVV, exp_date) VALUES (?, ?, ?, ?)");
            statement.setString(1, Holder_name);
            statement.setString(2, Card_number);
            statement.setString(3, CVV);
            statement.setString(4, EXP_date);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertPaypal(String EmailOrMobile, String Pass) {
        try { // byinsert paypal information fel database
            PreparedStatement statement = connection.prepareStatement("INSERT INTO paypal (Email_or_phone_number,password) VALUES (?, ?)");
            statement.setString(1, EmailOrMobile);
            statement.setString(2, Pass);
             statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

public void closeConnection() {
    try {  //by2fl el connection
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}


