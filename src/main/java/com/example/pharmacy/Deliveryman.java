package com.example.pharmacy;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deliveryman {

    public ResultSet select(String searchByWhat, String name) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        Statement state = conn.createStatement();
        String query = "SELECT * FROM drugs WHERE `" + searchByWhat + "` = '" + name + "';";
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

    public void insert(String name, String quantity, String manufacturer,String symptomatology ,String price, String discount) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "INSERT INTO `drugs`( `name`, `quantity`, `manufacturer`, `symptomatology`, `price`, `discount`) VALUES ('" + name + "','" + quantity + "','" + manufacturer + "','" + symptomatology + "','" + price + "','" + discount + "')";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.executeUpdate();
        prs.close();
    }

    public void insertIntoForDeliveryTable(String name, String quantity) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();

        LocalDate d = LocalDate.now(); // Gets the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = d.format(formatter);

        String query = "INSERT INTO `for_deliveryman`(`name`, `quantity`,`date`) VALUES ('" + name + "','" + quantity + "','" + date + "')";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.execute();
        prs.close();
        conn.close();
    }

    //7
    public void deliver(String name, String quantity) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        ResultSet res = select("name", name);



        if(!(res.next())){

            Statement state = conn.createStatement();
            String query = "SELECT * FROM `new_drug_for_delivery` WHERE `name` = '" + name + "';";
            res = state.executeQuery(query);

            int quantity2 = 0;

            while(res.next()){
                quantity2 = res.getInt("quantity");
                insert(res.getString("name"), quantity, res.getString("manufacturer"), res.getString("symptomatology"), res.getString("price"), res.getString("discount"));
            }

            insertIntoForDeliveryTable(name, String.valueOf(quantity2 - Integer.valueOf(quantity)));

            delete("new_drug_for_delivery", "name" , name);


        }else{


            int quantityBD = 0;

            res = select("name", name);

            while(res.next()){
                quantityBD = res.getInt("quantity");
            }

            String query;

            query = "UPDATE `drugs` SET `quantity` = '" + (quantityBD + Integer.valueOf(quantity)) + " ' WHERE `name` = '" + name + "';";
            PreparedStatement prs = conn.prepareStatement(query);
            prs.executeUpdate();
            prs.close();


            quantityBD = 0;
            res = select("name", name);
            while(res.next()){
                quantityBD = res.getInt("quantity");
            }

            if(quantityBD <= Integer.valueOf(quantity)){
                delete("for_deliveryman", "name", name);
            }else{
                query = "UPDATE `for_deliveryman` SET `quantity`= `quantity` - '" + quantity + "' WHERE `name` = '" + name + "'";
                PreparedStatement pr = conn.prepareStatement(query);
                pr.executeUpdate();
                pr.close();
            }
        }

        System.out.println("Successfully updated!");


        insert(name, quantity);


        System.out.println("Successfully updated! [delivered]");
        //UPDATE `drugs` SET `quantity`= `quantity` - '20' WHERE `ID` = '1'

    }

    public void delete(String nameTable, String whereColumn, String whereValue) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "DELETE FROM `" + nameTable + "` WHERE `" + whereColumn + "` = '" + whereValue + "';";
        PreparedStatement pr = conn.prepareStatement(query);
        pr.executeUpdate();
        pr.close();
        conn.close();
    }

    public void insert(String name, String quantity) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        LocalDate d = LocalDate.now(); // Gets the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = d.format(formatter);
        int ID_drug = 0;
        ResultSet res = select("name", name);
        while(res.next()){
            ID_drug = res.getInt("ID");
        }

        String query = "INSERT INTO `delivered`(`name`, `quantity`, `delivered_date`, `ID_drug`) VALUES ('" + name + "','" + quantity + "','" + date + "','" + ID_drug + "')";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.execute();
        prs.close();
        conn.close();
    }

}
