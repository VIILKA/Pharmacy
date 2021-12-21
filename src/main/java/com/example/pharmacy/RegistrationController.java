package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationController {

    @FXML
    private ChoiceBox<String> AccType;

    @FXML
    private Button SignUpButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField loginTextField1;

    @FXML
    void initialize(){
        AccType.getItems().add("Pharmacist");
        AccType.getItems().add("Deliveryman");
        AccType.setValue("Pharmacist");
    }

    @FXML

    boolean isNumeric(String str){
        try{
            Double.parseDouble(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    @FXML
    void ClickSignUp(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        errorLabel.setText("");
        Authoziration au = new Authoziration();

        ResultSet res = au.select("authorization", "login", loginTextField.getText());

        if(res.next()){
            errorLabel.setText("this login is already taken");
            return;
        }else if(loginTextField.getText().isEmpty()){
            errorLabel.setText("please enter data");
            return;
        }

        String accT = "1";
        if(AccType.getValue().equals("Pharmacist")){
            accT = "1";
        }else{
            accT = "2";
        }

        au.insert(loginTextField.getText(), loginTextField1.getText(), accT);

        loginTextField.setText("");
        loginTextField1.setText("");

        Stage stage1 = (Stage) loginTextField.getScene().getWindow();
        stage1.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("2_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 520);
        stage.setTitle("Authorization");
        stage.setScene(scene);
        stage.show();
        System.out.println("Authorization");
    }

}
