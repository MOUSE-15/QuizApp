package com.example.quizapp.DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
    private static String name = "Mohammed";
    private static String DBName = "quizApp";
    private static String password = "eminence_07";
    private static String url="jdbc:mysql://localhost:3306/" + DBName + "?useSSL=false";

    public static Connection connect() throws SQLException, ClassNotFoundException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, name, password);
        } catch (SQLException | ClassNotFoundException e) {

            System.out.println("ConnectionException: " +e);;

        }
        return null;
    }
}
