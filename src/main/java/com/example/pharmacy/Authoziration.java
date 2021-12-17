package com.example.pharmacy;


import java.sql.*;

public class Authoziration {

    public ResultSet select(String nameTable , String searchByWhat, String name) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        Statement state = conn.createStatement();
        String query = "SELECT * FROM `" + nameTable + "` WHERE `" + searchByWhat + "` = '" + name + "';";
        ResultSet res = state.executeQuery(query);
        return res;
    }

    public ResultSet select(String nameTable) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        Statement state = conn.createStatement();
        String query = "SELECT * FROM `" + nameTable + "`;";
        ResultSet res = state.executeQuery(query);
        return res;
    }

    public void delete(String nameTable, String whereColumn, String whereValue) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "DELETE FROM `" + nameTable + "` WHERE `" + whereColumn + "` = '" + whereValue + "';";
        PreparedStatement pr = conn.prepareStatement(query);
        pr.close();
        conn.close();
    }

    public void insert(String login, String password, String ID_accType) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();

        String query = "INSERT INTO `authorization`(`login`, `password`, `ID_accType`) VALUES ('" + login + "','" + password +  "','" + ID_accType + "')";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.execute();
        prs.close();
        conn.close();
    }


    public void update(){

    }
}
