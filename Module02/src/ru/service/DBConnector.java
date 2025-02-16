package ru.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException throwable) {
            throwable.printStackTrace();
            throw new RuntimeException("Driver not found");
        }
    }

    public static Connection getConnection(String connectionString) {
        try {
            return DriverManager.getConnection(connectionString, "1", "1");
        } catch (SQLException throwable) {
            throw new RuntimeException("Connection error", throwable.getCause());
        }
    }
}
