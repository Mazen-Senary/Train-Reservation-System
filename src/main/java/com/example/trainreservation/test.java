package com.example.trainreservation;

public class test {
    public static void main(String[] args) {
        DB db = new DB();
        db.testConnection();
        db.closeConnection();
    }
}