package com.example.pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnection {

    public static Connection getConnected() throws SQLException, ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/apothekedb","root","");
            //here soon is database name, root is username and password
            System.out.println("Successfully connected to mySQL database!");
            return conn;
        }catch(Exception e){ System.out.println(e);}
        return null;
    }
}
