

//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("it works!");
//    }
package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private TextField InputField;

    @FXML
    private Label welcomeText;

    @FXML
    void onClickShow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("2_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 595, 395);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) InputField.getScene().getWindow();
        stage1.close();
        welcomeText.setText(InputField.getText());

    }

}
