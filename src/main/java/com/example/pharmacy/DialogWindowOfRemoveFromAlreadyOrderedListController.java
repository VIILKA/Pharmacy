package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.*;

public class DialogWindowOfRemoveFromAlreadyOrderedListController {

    @FXML
    private Button No;

    @FXML
    private Button Yes;

    private boolean choice;

    @FXML
    void ClickNo(ActionEvent event) {
        Stage stage1 = (Stage) No.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void ClickYes(ActionEvent event) throws SQLException, ClassNotFoundException {
        Deliveryman d = new Deliveryman();
        Connection conn = dbConnection.getConnected();

        ResultSet res = conn.createStatement().executeQuery("SELECT `value` FROM `dialogwindows` WHERE `name` = 'dialog window 1'");

        Statement state = conn.createStatement();

        while(res.next()) {
            String query = "SELECT * FROM `for_deliveryman` WHERE `name` = '" + res.getString("value") + "';";
            ResultSet res1 = state.executeQuery(query);
            if(res1.next()){
                d.delete("for_deliveryman", "name", res.getString("value"));
            }else{
                d.delete("new_drug_for_delivery", "name", res.getString("value"));
            }

            System.out.println("deleted");

        }

        res.close();
        state.close();
        conn.close();

        Stage stage1 = (Stage) No.getScene().getWindow();
        stage1.close();



    }

    public boolean getChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }
}
