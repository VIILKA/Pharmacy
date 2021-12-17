package com.example.pharmacy;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pharmacist {

    public ResultSet select(String searchByWhat, String name) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        Statement state = conn.createStatement();
        String query = "SELECT * FROM drugs WHERE `" + searchByWhat + "` = '" + name + "';";
        ResultSet res = state.executeQuery(query);
        return res;
    }

    public ResultSet select(String tableName,String searchByWhat, String name) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        Statement state = conn.createStatement();
        String query = "SELECT * FROM `" + tableName + "` WHERE `" + searchByWhat + "` = '" + name + "';";
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


    public void update(String setColumn, String setValue, String whereColumn, String whereValue) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "UPDATE `drugs` SET `" + setColumn + "` = '" + setValue + "' WHERE `drugs`.`" + whereColumn + "` = '" + whereValue + "'";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.executeUpdate();

        prs.close();
        conn.close();
        System.out.println("Successfully updated!");
    }

    public void update(String tableName,String setColumn, String setValue, String whereColumn, String whereValue) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "UPDATE `" + tableName + "` SET `" + setColumn + "` = '" + setValue + "' WHERE `" + whereColumn + "` = '" + whereValue + "'";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.executeUpdate();

        prs.close();
        conn.close();
        System.out.println("Successfully updated!");
    }

    //1, 5
    public void insertDrug(String name, String quantity, String manufacturer, String symptomatology, String price, String discount) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "INSERT INTO `drugs`(`name`, `quantity`, `manufacturer`, `symptomatology`, `price`, `discount`) VALUES ('" + name  + "','" + quantity + "','" + manufacturer + "','" + symptomatology + "','"+ price +"','"+ discount + "')";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.execute();
        prs.close();
        conn.close();
        System.out.println("Drug has been successfully added!");
    }



    //3

    //2
    public void deleteDrug(String id) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "DELETE FROM `Drugs` WHERE `ID_drug` = '" + id + "';";
        PreparedStatement pr = conn.prepareStatement(query);
        pr.close();
        conn.close();
        System.out.println("Drug has been successfuly deleted!");
    }

    //3


    public void insert(String name, String quantity,String price, String date, String discount, String ID_drug, String total) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "INSERT INTO `sold`(`name`, `quantity`,`price`, `date`, `discount`, `ID_drug`, `total`) VALUES ('" + name + "','" + quantity + "','" + price + "','" + date + "','" + discount + "','" + ID_drug + "','" + total + "')";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.execute();
        prs.close();
        conn.close();
    }

    public void insert(String name, String quantity, String manufacturer,String symptomatology ,String price, String discount) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "INSERT INTO `new_drug_for_delivery`( `name`, `quantity`, `manufacturer`, `symptomatology`, `price`, `discount`) VALUES ('" + name + "','" + quantity + "','" + manufacturer + "','" + symptomatology + "','" + price + "','" + discount + "')";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.executeUpdate();
        prs.close();

        System.out.println("Successfully inserted data into  new_drug_for_delivery  table!");
    }



    public void insert(String name, String quantity) throws SQLException, ClassNotFoundException {
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

    //4
    public void sellFunc(String name, String quantity, String discount) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "UPDATE `drugs` SET `quantity`= `quantity` - '" + quantity + "' WHERE `name` = '" + name + "'";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.executeUpdate();
        prs.close();

        double price = 0;
        int id_drug = 0;
        ResultSet res = select("name", name);
        while(res.next()){
            price = res.getDouble("price");
            id_drug = res.getInt("ID");
        }

        String old_price = String.valueOf(price);
        double g = (1-(Integer.valueOf(discount)/100.0));
        price = price * g;

        LocalDate d = LocalDate.now(); // Gets the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = d.format(formatter);

        double total = price * Integer.valueOf(quantity);

        insert(name, quantity, old_price, date, discount, String.valueOf(id_drug), String.valueOf(total));


        System.out.println("Successfully updated!");
        //UPDATE `drugs` SET `quantity`= `quantity` - '20' WHERE `ID` = '1'

    }

    //4

    public void sellFunc(String name, String quantity) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection.getConnected();
        String query = "UPDATE `drugs` SET `quantity`= `quantity` - '" + quantity + "' WHERE `name` = '" + name + "'";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.executeUpdate();
        prs.close();

        double price = 0;
        int id_drug = 0;
        int discount = 0;
        ResultSet res = select("name", name);
        while(res.next()){
            price = res.getDouble("price");
            id_drug = res.getInt("ID");
            discount = res.getInt("discount");

        }

        String old_price = String.valueOf(price);
        double g = (1-(discount/100.0));
        price = price * g;

        LocalDate d = LocalDate.now(); // Gets the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = d.format(formatter);

        double total = price * Integer.valueOf(quantity);

        insert(name, quantity, old_price, date, String.valueOf(discount), String.valueOf(id_drug), String.valueOf(total));


        System.out.println("Successfully updated!");
        //UPDATE `drugs` SET `quantity`= `quantity` - '20' WHERE `ID` = '1'

    }

//    public void makeCheck(int how_many_last_rows) throws SQLException, ClassNotFoundException {
//        try{
//            Connection conn = dbConnection.getConnected();
//            Statement state = conn.createStatement();
//            String query = "SELECT * FROM ( SELECT * FROM sold ORDER BY ID DESC LIMIT " + how_many_last_rows + ")Var1 ORDER BY ID ASC;";
//            ResultSet res = state.executeQuery(query);
//
//
//            String file_name = "D:\\курсач\\Check(1).pdf";
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream(file_name));
//            document.open();
//            while(res.next()) {
//                Paragraph para = new Paragraph(res.getString("name"));
//                Paragraph para2 = new Paragraph(res.getString("quantity"));
//                Paragraph para3 = new Paragraph(res.getString("price"));
//                Paragraph para4 = new Paragraph(res.getString("date"));
//                Paragraph para5 = new Paragraph(res.getString("total"));
//                document.add(para);
//                document.add(para2);
//                document.add(para3);
//                document.add(para4);
//                document.add(para5);
//            }
//            document.close();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    //}




}
