package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DialogNewDrugWindowController {

    @FXML
    private Button No;

    @FXML
    private Button Yes;

    @FXML
    void ClickNo(ActionEvent event) {
        Stage stage1 = (Stage) No.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void ClickYes(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) No.getScene().getWindow();
        stage1.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewDrugOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 524, 352);
        stage.setTitle("New Drug");
        stage.setScene(scene);
        stage.show();

    }

}
