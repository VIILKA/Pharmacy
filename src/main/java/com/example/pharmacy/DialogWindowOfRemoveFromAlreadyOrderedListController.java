package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    void ClickYes(ActionEvent event) {

        choice = true;


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
