package com.company.TestOfTables;


import java.util.*;
import java.sql.*;

import com.company.Connection.JDBC;

public class Task {
    // Сделайте выборку по авторам, отсортировав по их Имени и Фамилии
    public static void task1() throws SQLException    {
        String query = "SELECT * FROM authors ORDER BY \"firstName\", \"lastName\"";
        ResultSet rs1 = JDBC.connection.createStatement().executeQuery(query);
        while (rs1.next()) {int id = rs1.getInt("authorID");
            String firstName = rs1.getString("firstName");
            String lastName = rs1.getString("lastName");
            System.out.println(id + "\t" + firstName + "\t" + lastName);
        }
    }
    // Добавьте нового Издателя (publusher).
    public static void task2() throws SQLException
    {
        try {
            JDBC.connection.createStatement().executeUpdate("INSERT INTO publishers(\"publisherName\") VALUES ('Amongus')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Сделайте выборку Издателей и измените имя определенного Издателя.
    public static void task3() throws SQLException
    {        String query = "SELECT * FROM publishers WHERE \"publisherID\" = 10";
        ResultSet rs1 = null;
        try {
            rs1 = JDBC.connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            rs1.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println(rs1.getInt("publisherID") + "\t" + rs1.getString("publisherName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            JDBC.connection.createStatement().executeUpdate("UPDATE publishers SET \"publisherName\" = 'Valley' WHERE \"publisherID\" = 10");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            rs1 = JDBC.connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            rs1.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println(rs1.getInt("publisherID") + "\t" + rs1.getString("publisherName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Предоставьте отсортированный список книг определенного издателя (при этом id требуемого издателя можно менять в sql запросе)
    public static void task4() throws SQLException    {
        String query = "SELECT * FROM titles WHERE \"publisherID\" = 1 ORDER BY title";        ResultSet rs1 = JDBC.connection.createStatement().executeQuery(query);
        while (rs1.next())        {
            System.out.println(rs1.getString("isbn") + "\t"                    + rs1.getString("title") + "\t"
                    + rs1.getInt("editionNumber") + "\t"                    + rs1.getString("year") + "\t"
                    + rs1.getInt("publisherID") + "\t"                    + rs1.getDouble("price"));
        }    }
    // Выполните добавление Нового автора в БД
    public static void task5() throws SQLException    {
        JDBC.connection.createStatement().executeUpdate("INSERT INTO authors(\"firstName\", \"lastName\") VALUES ('Yevgeny', 'Zamyatin')");    }
    // Обновите Имя автора по определенному id
    public static void task6() throws SQLException    {
        JDBC.connection.createStatement().executeUpdate("UPDATE authors SET \"firstName\" = 'Joanne', \"lastName\"='Rowling' WHERE \"authorID\"=11");    }
    // там длинное условие
    public static void task7() throws SQLException    {
        JDBC.connection.createStatement().executeUpdate("INSERT INTO publishers(\"publisherName\") VALUES ('Amongus')");        JDBC.connection.createStatement().executeUpdate("INSERT INTO titles(isbn, title, \"editionNumber\", year, \"publisherID\", price) VALUES\r\n"
                + " ('5343175416', 'Demons', 1, 1888, (select \"publisherID\" from publishers where \"publisherName\" = 'Amongus'), 8.82);");        JDBC.connection.createStatement().executeUpdate("INSERT INTO authorisbn VALUES ((SELECT \"authorID\" "
                + "FROM authors WHERE \"firstName\" = 'Fyodor' AND \"lastName\" = 'Dostoevsky'), '5343175416');");    }
}