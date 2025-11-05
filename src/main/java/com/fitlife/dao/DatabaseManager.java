package com.fitlife.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {

   
    private static final String DATABASE_URL = "jdbc:sqlite:fitlife.db";


    public static Connection getConnection() throws SQLException {
        try {
          
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
           
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        }

       
        return DriverManager.getConnection(DATABASE_URL);
    }
}