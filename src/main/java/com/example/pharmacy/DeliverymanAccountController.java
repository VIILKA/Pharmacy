package com.example.pharmacy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliverymanAccountController {

    @FXML
    private TableColumn<NeedDeliv, String> InDeliverDrugsNameOrderedColumn;

    @FXML
    private TableView<NeedDeliv> InDeliverDrugsOrderedListTableView;

    @FXML
    private TableColumn<NeedDeliv, String> InDeliverDrugsQuantityOrderedColumn;

    @FXML
    private TableColumn<Delivered, String> InShowDeliveredDeliveredDateColumn;

    @FXML
    private TableView<Delivered> InShowDeliveredDeliveredTableView;

    @FXML
    private TableColumn<Delivered, String> InShowDeliveredNameColumn;

    @FXML
    private TableColumn<Delivered, String> InShowDeliveredQuantityColumn;

    @FXML
    private TextField InShowDeliveredSearchTextField;

    @FXML
    private Button InDeliverDrugsRemoveButton;

    @FXML
    private Button BackToAuthorizationButton;

    @FXML
    private Button DeliverDrugsButton;

    @FXML
    private AnchorPane DeliverDrugsWindow;

    @FXML
    private Button HelpButton;

    @FXML
    private AnchorPane HelpWindow;

    @FXML
    private Button InDeliverDrugsAddButton;

    @FXML
    private TableColumn<DelivDrugs, String> InDeliverDrugsNameColumn;

    @FXML
    private Label InDeliverDrugsNameErrorLabel;

    @FXML
    private TextField InDeliverDrugsNameTextField;

    @FXML
    private Button InDeliverDrugsDeliverButton;

    @FXML
    private TableColumn<DelivDrugs, String> InDeliverDrugsQuantityColumn;

    @FXML
    private Label InDeliverDrugsQuantityErrorLabel;

    @FXML
    private TextField InDeliverDrugsQuantityTextField;

    @FXML
    private Label InDeliverDrugsSuccesLabel;

    @FXML
    private TableView<DelivDrugs> InDeliverDrugsToDeliverListTableView;


    @FXML
    private Button SearchDrugButton;

    @FXML
    private AnchorPane SearchWindow;

    @FXML
    private Button ShowDeliveredDrugsButton;

    @FXML
    private AnchorPane ShowWindow;

    @FXML
    private TableView<DrugsDeliv> InSearchDrugsListTableView;

    @FXML
    private TableColumn<DrugsDeliv, String> InSearchNameColumn;

    @FXML
    private TableColumn<DrugsDeliv, String> InSearchPriceColumn;

    @FXML
    private TextField InSearchSearchTextfield;

    @FXML
    private TableColumn<DrugsDeliv, String> InsearchQuantityColumn;

    ObservableList<DrugsDeliv> drugsList = FXCollections.observableArrayList();
    ObservableList<NeedDeliv> needDelivList = FXCollections.observableArrayList();
    ObservableList<DelivDrugs> delivDrugsListValue = FXCollections.observableArrayList();
    ObservableList<Delivered> deliveredTableValues = FXCollections.observableArrayList();
    Deliveryman d = new Deliveryman();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        SearchWindow.setVisible(false);
        DeliverDrugsWindow.setVisible(false);
        ShowWindow.setVisible(false);
        HelpWindow.setVisible(false);
    }

    @FXML
    void ClickInDeliverDrugsRemoveButton(ActionEvent event) {
        InDeliverDrugsToDeliverListTableView.getItems().removeAll(InDeliverDrugsToDeliverListTableView.getSelectionModel().getSelectedItem());
        System.out.println("Successfully removed from list!");

    }

    @FXML
    void ClickBackToAuthorizationButton(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) BackToAuthorizationButton.getScene().getWindow();
        stage1.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("2_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 510);
        stage.setTitle("Authorization");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ClickDeliverDrugsButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        SearchWindow.setVisible(false);
        DeliverDrugsWindow.setVisible(false);
        ShowWindow.setVisible(false);
        HelpWindow.setVisible(false);

        DeliverDrugsWindow.setVisible(true);

        InDeliverDrugsNameErrorLabel.setText("");
        InDeliverDrugsQuantityErrorLabel.setText("");
        InDeliverDrugsSuccesLabel.setText("");

        d.delete("for_deliveryman", "quantity", "0");


        ResultSet res = d.select("for_deliveryman");
        ResultSet res2 = d.select("new_drug_for_delivery");
        needDelivList.clear();

        while(res.next()){
            needDelivList.add(new NeedDeliv(res.getString("name"), res.getString("quantity")));
        }

        while(res2.next()){
            needDelivList.add(new NeedDeliv(res2.getString("name"), res2.getString("quantity")));
        }


        InDeliverDrugsNameOrderedColumn.setCellValueFactory(new PropertyValueFactory<NeedDeliv, String>("name"));
        InDeliverDrugsQuantityOrderedColumn.setCellValueFactory(new PropertyValueFactory<NeedDeliv, String>("quantity"));
        InDeliverDrugsOrderedListTableView.setItems(needDelivList);

        delivDrugsListValue.clear();


    }

    @FXML
    void ClickHelpButton(ActionEvent event) {
        SearchWindow.setVisible(false);
        DeliverDrugsWindow.setVisible(false);
        ShowWindow.setVisible(false);
        HelpWindow.setVisible(false);

        HelpWindow.setVisible(true);
    }


    @FXML
    void ClickSearchDrugButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        SearchWindow.setVisible(false);
        DeliverDrugsWindow.setVisible(false);
        ShowWindow.setVisible(false);
        HelpWindow.setVisible(false);

        SearchWindow.setVisible(true);

        drugsList.clear();

        ResultSet res = d.select("drugs");

        InSearchNameColumn.setCellValueFactory(new PropertyValueFactory<DrugsDeliv, String>("name"));
        InsearchQuantityColumn.setCellValueFactory(new PropertyValueFactory<DrugsDeliv, String>("quantity"));
        InSearchPriceColumn.setCellValueFactory(new PropertyValueFactory<DrugsDeliv, String>("price"));

        while(res.next()){
            drugsList.add(new DrugsDeliv(res.getString("name"), res.getString("quantity"), res.getString("price")));
        }

        InSearchDrugsListTableView.setItems(drugsList);

        //////

        FilteredList<DrugsDeliv> filteredData = new FilteredList<>(drugsList, b -> true);
        InSearchSearchTextfield.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(DrugsDeliv -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (DrugsDeliv.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches type
            }else if (DrugsDeliv.getQuantity().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filtephappr matches madeby
            }  else return String.valueOf(DrugsDeliv.getPrice()).contains(lowerCaseFilter);// Filter matches cost
        }));
        SortedList<DrugsDeliv> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(InSearchDrugsListTableView.comparatorProperty());
        InSearchDrugsListTableView.setItems(sortedData);
    }

    @FXML
    void ClickShowDeliveredDrugsButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        SearchWindow.setVisible(false);
        DeliverDrugsWindow.setVisible(false);
        ShowWindow.setVisible(false);
        HelpWindow.setVisible(false);

        ShowWindow.setVisible(true);



        deliveredTableValues.clear();

        ResultSet res = d.select("delivered");

        InShowDeliveredNameColumn.setCellValueFactory(new PropertyValueFactory<Delivered, String>("name"));
        InShowDeliveredQuantityColumn.setCellValueFactory(new PropertyValueFactory<Delivered, String>("quantity"));
        InShowDeliveredDeliveredDateColumn.setCellValueFactory(new PropertyValueFactory<Delivered, String>("date"));

        while(res.next()){
            deliveredTableValues.add(new Delivered(res.getString("name"), res.getString("quantity"), res.getString("delivered_date")));
        }

        InShowDeliveredDeliveredTableView.setItems(deliveredTableValues);

        //////

        FilteredList<Delivered> filteredData = new FilteredList<>(deliveredTableValues, b -> true);
        InShowDeliveredSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Delivered -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (Delivered.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches type
            }else if (Delivered.getQuantity().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filtephappr matches madeby
            }  else return String.valueOf(Delivered.getDate()).contains(lowerCaseFilter);// Filter matches cost
        }));
        SortedList<Delivered> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(InShowDeliveredDeliveredTableView.comparatorProperty());
        InShowDeliveredDeliveredTableView.setItems(sortedData);

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
    void InDeliverDrugsClickAddButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        InDeliverDrugsNameErrorLabel.setText("");
        InDeliverDrugsQuantityErrorLabel.setText("");
        InDeliverDrugsSuccesLabel.setText("");

        ResultSet res1 = d.select("for_deliveryman");
        ObservableList<String> res = FXCollections.observableArrayList();
        ObservableList<String> res11 = FXCollections.observableArrayList();

        while(res1.next()){
            res.add(res1.getString("name"));
        }

        res1 = d.select("new_drug_for_delivery");

        while(res1.next()){
            res11.add(res1.getString("name"));
        }


        if(InDeliverDrugsNameTextField.getText().isEmpty()){
            InDeliverDrugsNameErrorLabel.setText("Invalid name");
            return;
        }else if(!(res.contains(InDeliverDrugsNameTextField.getText())) && !(res11.contains(InDeliverDrugsNameTextField.getText()))){
            InDeliverDrugsNameErrorLabel.setText("Such drug was not ordered");
            return;
        }else if(!isNumeric(InDeliverDrugsQuantityTextField.getText()) || InDeliverDrugsQuantityTextField.getText().isEmpty()){
            InDeliverDrugsQuantityErrorLabel.setText("Invalid Quantity");
            return;
        }

        InDeliverDrugsNameColumn.setCellValueFactory(new PropertyValueFactory<DelivDrugs, String>("name"));
        InDeliverDrugsQuantityColumn.setCellValueFactory(new PropertyValueFactory<DelivDrugs, String>("quantity"));
        boolean v = true;
        for(DelivDrugs o : delivDrugsListValue){
            if(o.getName().equals(InDeliverDrugsNameTextField.getText())){
                v = false;
                delivDrugsListValue.remove(o);
                delivDrugsListValue.add(new DelivDrugs(InDeliverDrugsNameTextField.getText(), String.valueOf(Integer.valueOf(o.getQuantity()) + Integer.valueOf(InDeliverDrugsQuantityTextField.getText()))));
            }
        }

        if(v) {
            delivDrugsListValue.add(new DelivDrugs(InDeliverDrugsNameTextField.getText(), InDeliverDrugsQuantityTextField.getText()));
        }



        InDeliverDrugsToDeliverListTableView.setItems(delivDrugsListValue);




    }

    @FXML
    void InDeliverDrugsClickDeliverButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        InDeliverDrugsNameErrorLabel.setText("");
        InDeliverDrugsQuantityErrorLabel.setText("");
        InDeliverDrugsSuccesLabel.setText("");

        for(DelivDrugs s : delivDrugsListValue){
            d.deliver(s.getName(), s.getQuantity());
            System.out.println(s.getQuantity());
        }
        InDeliverDrugsSuccesLabel.setText("Successfully delivered");
        System.out.println("Successfully delivered!");

        ClickDeliverDrugsButton(new ActionEvent());
    }

}
