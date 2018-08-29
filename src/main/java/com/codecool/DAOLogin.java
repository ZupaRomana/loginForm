package com.codecool;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOLogin {

    private Connection connection;

    public DAOLogin() {
        this.getConnection();
    }

    private void getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/loginform", "roman", "test");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String getLogin() {

    }
}
