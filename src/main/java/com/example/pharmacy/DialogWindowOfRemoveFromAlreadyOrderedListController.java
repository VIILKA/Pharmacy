package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        while(res.next()) {
                System.out.println("deleted");
                d.delete("for_deliveryman", "name", res.getString("value"));
        }

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
