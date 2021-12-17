package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class NewDrugOrderController {

    @FXML
    private TextField Discount;

    @FXML
    private Label DiscountError;

    @FXML
    private TextField Manufacturer;

    @FXML
    private Label ManufacturerError;

    @FXML
    private TextField Name;

    @FXML
    private Label NameError;

    @FXML
    private Button Order;

    @FXML
    private TextField Price;

    @FXML
    private Label PriceError;

    @FXML
    private TextField Quantity;

    @FXML
    private Label QuantityError;

    @FXML
    private TextField Symptomatology;

    @FXML
    private Label SymptomatologyError;

    @FXML
    private Label SuccessMassege;


    Pharmacist p = new Pharmacist();

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
    void ClickOrder(ActionEvent event) throws SQLException, ClassNotFoundException {


        if(Name.getText().isEmpty()){
            NameError.setText("Invalid name");
            return;
        }else if(!isNumeric(Quantity.getText()) || Quantity.getText().isEmpty() || Quantity.getText().contains(".") || Quantity.getText().contains(",") ){
            QuantityError.setText("Invalid Quantity");
            return;
        }else if(Manufacturer.getText().isEmpty()){
            ManufacturerError.setText("Invalid manufacturer");
            return;
        }else if(Symptomatology.getText().isEmpty()){
            SymptomatologyError.setText("Invalid symptomatology");
            return;
        }else if(!isNumeric(Price.getText()) || Price.getText().isEmpty() || Price.getText().contains(".") || Price.getText().contains(",") ){
            PriceError.setText("Invalid price");
            return;
        }else if(!isNumeric(Discount.getText()) || Discount.getText().isEmpty() || Discount.getText().contains(".") || Discount.getText().contains(",") ){
            DiscountError.setText("Invalid discount");
            return;
        }

        p.insert(Name.getText(), Quantity.getText(), Manufacturer.getText(), Symptomatology.getText(), Price.getText(), Discount.getText());
        System.out.println("Successfully data inserted!");
        SuccessMassege.setText("Successfully ordered!");

        Stage stage1 = (Stage) Quantity.getScene().getWindow();
        stage1.close();


    }

}
