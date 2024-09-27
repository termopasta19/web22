package com.company.Connection;

import java.util.*;
import java.sql.*;

public class JDBC {
    public static Connection connection = null;

    public static void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQl JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQl JDBC Driver Not Registered!");
            e.printStackTrace();
            throw new SQLException("MySQl JDBC Driver Not Registered!");
        }

        connection = DriverManager.getConnection("jdbc:mysql://localhost/books2?useUnicode=true&serverTimezone=UTC", "root", "Kapfen222.ko");
        if (connection == null) {
            throw new SQLException();
        } else {
            System.out.println("Successfully connected to MySQL!");
        }

    }

    public static void close() {
        try {
            if(connection != null) {
                connection.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection!");
        }
    }

}



