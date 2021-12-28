package com.example.pharmacy;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

public class PharmacistAccountController {
    Pharmacist p = new Pharmacist();
    ObservableList<Drugs> tableValues = FXCollections.observableArrayList();
    ObservableList<SellList> SellingListTableValues = FXCollections.observableArrayList();
    ObservableList<OrderList> OrderListValues = FXCollections.observableArrayList();
    ObservableList<OrderedList> OrderedListValues = FXCollections.observableArrayList();
    ObservableList<SoldList> SoldListValues = FXCollections.observableArrayList();
    double total_price = 0;


    @FXML
    private TableColumn<SoldList, String> InSoldAnchopaneDateColumn;

    @FXML
    private TableColumn<SoldList, String> InSoldAnchopaneDiscountColumn;

    @FXML
    private TableColumn<SoldList, String> InSoldAnchopaneNameColumn;

    @FXML
    private TableColumn<SoldList, String> InSoldAnchopaneQuantityColumn;

    @FXML
    private TextField InSoldAnchopaneSearchTextField;

    @FXML
    private TableView<SoldList> InSoldAnchopaneSoldTableView;

    @FXML
    private TableColumn<SoldList, String> InSoldAnchopaneTotalColumn;

    @FXML
    private TableColumn<SoldList, String> InSoldAnchorpanePriceColumn;

    @FXML
    private Button SoldButton;

    @FXML
    private AnchorPane SoldWindow;

    @FXML
    private Label SuccessMassageFromRemove;

    @FXML
    private TableView<OrderedList> OrderedListTableViewInOrderAnchorpane;

    @FXML
    private TableColumn<OrderedList, String> NameOrderedListColumnInOrderAnchorpane;

    @FXML
    private TableColumn<OrderedList, String> QuantityOrderedListColumnInOrderAnchorpane;

    @FXML
    private Button AddButtonInOrderAnchorpane;

    @FXML
    private TableColumn<OrderList, String> NameColumnInOrderAnchorpane;

    @FXML
    private Label NameErrorInOrderAnchorpane;

    @FXML
    private TextField NameTextFiealInOrderAnchorpane;

    @FXML
    private Button OrderButtonInOrderAnchorpane;

    @FXML
    private TableView<OrderList> OrderListTableViewInOrderAnchorpane;

    @FXML
    private TableColumn<OrderList, String> QuantityColumnInOrderAnchorpane;

    @FXML
    private Label QuantityErrorInOrderAnchorpane;

    @FXML
    private TextField QuantityTextfieldInOrderAnchorpane;

    @FXML
    private Button RemoveButtonInSellAnchorpane;

    @FXML
    private Button AddToListButton;
    @FXML
    private Button BackToAuthorization;

    @FXML
    private Button ChangeButton;

    @FXML
    private Button ChangePriceButton;

    @FXML
    private TableColumn<Drugs, String> Discount;

    @FXML
    private TextField DiscountForSellTextField;

    @FXML
    private Button HelpButton;

    @FXML
    private Label TotalCostLabelInSellAnchorpane;

    @FXML
    private TableColumn<SellList, String> NameColumnInSellAnchorpane;

    @FXML
    private TableColumn<SellList, String> QuantityColumnInSellAnchorpane;

    @FXML
    private TableColumn<SellList, String> discountColumnInSellAnchorpane;

    @FXML
    private TableColumn<Drugs, String> ID;

    @FXML
    private TableColumn<Drugs, String> Manufacturer;

    @FXML
    private AnchorPane MiniWindow1;

    @FXML
    private TableColumn<Drugs, String> Name;

    @FXML
    private Label NameErrorTextField;

    @FXML
    private TextField NameForSellTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private Button OrderDrugButton;

    @FXML
    private Label PharmacistAccLabel;

    @FXML
    private TableColumn<Drugs, String> Price;

    @FXML
    private Label PriceErrorTextField;

    @FXML
    private TextField PriceTextField;

    @FXML
    private TableColumn<Drugs, String> Quantity;

    @FXML
    private TextField QuantityForSellTextField;

    @FXML
    private Button SearchDrugButton;

    @FXML
    private TextField SearchLabel;

    @FXML
    private Button SellButton;

    @FXML
    private TableView<SellList> SellingDrugListTable;

    @FXML
    private TableColumn<Drugs, String> Symptomatology;

    @FXML
    private Button sellButtonInSellAnchorpane;

    @FXML
    private TableView<Drugs> tableOfDrugs;

    @FXML
    private AnchorPane windowChangePrice;

    @FXML
    private AnchorPane windowHelp;

    @FXML
    private AnchorPane windowOrder;

    @FXML
    private AnchorPane windowSell;

    @FXML
    private Label SuccessPriceChangeTextField1;

    @FXML
    private Label DiscountErrorInSellAnchorpane;

    @FXML
    private Label nameErrorInSellAnchorpane;

    @FXML
    private Label quantityErrorInSellAnchorpane;

    @FXML
    private Button RemoveButtonInOrderAnchorpane;

    @FXML
    private Label SuccessLabelInOrderAnchorpane;

    @FXML
    void ClickRemoveButtonInOrderAnchorpane(ActionEvent event) {
        OrderListTableViewInOrderAnchorpane.getItems().removeAll(OrderListTableViewInOrderAnchorpane.getSelectionModel().getSelectedItem());
        System.out.println("Succesfully removed from list!");
    }

    @FXML
    void ClickOrderButtonInOrderAnchorpane(ActionEvent event) throws SQLException, ClassNotFoundException {

        ResultSet res = p.select("for_deliveryman");

        HashSet<String> list = new HashSet<>();

        while(res.next()){
            list.add(res.getString("name"));
        }

        for(OrderList s: OrderListValues){
            if(list.contains(s.getName())){
                ResultSet res2 = p.select("for_deliveryman", "name", s.getName());
                String q = "";

                while(res2.next()){
                    q = res2.getString("quantity");
                }

                if(q.equals("")){
                    q = "0";
                }

                System.out.println(q + " " + s.getQuantity());
                System.out.println(String.valueOf(Integer.valueOf(q) + Integer.valueOf(s.getQuantity())));
                p.update("for_deliveryman","quantity", String.valueOf(Integer.valueOf(q) + Integer.valueOf(s.getQuantity())), "name", s.getName());

            }else {
                p.insert(s.getName(), s.getQuantity());
            }
        }
        OrderListValues.clear();
        OrderListTableViewInOrderAnchorpane.setItems(OrderListValues);
        ClickOnOrderDrugButton(new ActionEvent());

        SuccessLabelInOrderAnchorpane.setText("Successfuly Ordered!");
        System.out.println("Successfuly add to order list!");
    }

    @FXML
    void ClickAddButtonInOrderAnchorpane(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        NameErrorInOrderAnchorpane.setText("");
        QuantityErrorInOrderAnchorpane.setText("");

        ResultSet res1 = p.select("drugs");
        ObservableList<String> res = FXCollections.observableArrayList();

        while(res1.next()){
            res.add(res1.getString("name"));
        }



        if(!res.contains(NameTextFiealInOrderAnchorpane.getText())){
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogWindowOfNewDrug.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 276, 160);
            stage.setTitle("New Drug");
            stage.setScene(scene);
            stage.show();
            return;
        }


        if(NameTextFiealInOrderAnchorpane.getText().isEmpty()){
            NameErrorInOrderAnchorpane.setText("Invalid Name!");
            return;
        }else if(!isNumeric(QuantityTextfieldInOrderAnchorpane.getText()) || QuantityTextfieldInOrderAnchorpane.getText().isEmpty()){
            QuantityErrorInOrderAnchorpane.setText("Invalid Quantity");
            return;
        }

        NameColumnInOrderAnchorpane.setCellValueFactory(new PropertyValueFactory<OrderList, String>("name"));
        QuantityColumnInOrderAnchorpane.setCellValueFactory(new PropertyValueFactory<OrderList, String>("quantity"));

        boolean v = true;
        for(OrderList o : OrderListValues){
            if(o.getName().equals(NameTextFiealInOrderAnchorpane.getText())){
                v = false;
                OrderListValues.remove(o);
                OrderListValues.add(new OrderList(NameTextFiealInOrderAnchorpane.getText(), String.valueOf(Integer.valueOf(o.getQuantity()) + Integer.valueOf(QuantityTextfieldInOrderAnchorpane.getText()))));
            }
        }

        if(v) {
            OrderListValues.add(new OrderList(NameTextFiealInOrderAnchorpane.getText(), QuantityTextfieldInOrderAnchorpane.getText()));
        }



        OrderListTableViewInOrderAnchorpane.setItems(OrderListValues);
        System.out.println("Successfully added to list!");
    }

    @FXML
    void ClickAddToListButton(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        total_price =0;
        System.out.println(total_price);
        nameErrorInSellAnchorpane.setText("");
        quantityErrorInSellAnchorpane.setText("");
        DiscountErrorInSellAnchorpane.setText("");

        ResultSet res1 = p.select("drugs");
        ObservableList<String> res = FXCollections.observableArrayList();

        while(res1.next()){
            res.add(res1.getString("name"));
        }



        if(!res.contains(NameForSellTextField.getText())){
            nameErrorInSellAnchorpane.setText("There is no such drug!");
            return;
        }else if(!isNumeric(QuantityForSellTextField.getText()) || QuantityForSellTextField.getText().isEmpty() || QuantityForSellTextField.getText().contains(".") || QuantityForSellTextField.getText().contains(",") ){
            quantityErrorInSellAnchorpane.setText("Invalid Quantity");
            return;
        }
        else if(!isNumeric(DiscountForSellTextField.getText()) || DiscountForSellTextField.getText().isEmpty()){
            DiscountErrorInSellAnchorpane.setText("Invalid Discount");
            return;
        }

        NameColumnInSellAnchorpane.setCellValueFactory(new PropertyValueFactory<SellList, String>("Name"));
        QuantityColumnInSellAnchorpane.setCellValueFactory(new PropertyValueFactory<SellList, String>("Quantity"));
        discountColumnInSellAnchorpane.setCellValueFactory(new PropertyValueFactory<SellList, String>("Discount"));

        boolean v = true;
        for(SellList o : SellingListTableValues){
            if(o.getName().equals(NameForSellTextField.getText())){
                v = false;
                SellingListTableValues.remove(o);
                SellingListTableValues.add(new SellList(NameForSellTextField.getText(), String.valueOf(Integer.valueOf(o.getQuantity()) + Integer.valueOf(QuantityForSellTextField.getText())), DiscountForSellTextField.getText()));
            }
        }

        if(v) {
            SellingListTableValues.add(new SellList(NameForSellTextField.getText(), QuantityForSellTextField.getText(), DiscountForSellTextField.getText()));
        }



        SellingDrugListTable.setItems(SellingListTableValues);



        for(SellList n : SellingListTableValues){
            res1 = p.select("name", n.getName());

            while(res1.next()){
                double g = (1-(Integer.valueOf(n.getDiscount())/100.0));
                double s = res1.getInt("price") * g * Integer.valueOf(n.getQuantity());
                System.out.println(total_price);
                total_price = total_price + s;

            }
        }


        TotalCostLabelInSellAnchorpane.setText("Total cost: "+String.valueOf(total_price));
        System.out.println(total_price);




    }

    @FXML
    void ClickChangePriceButton(ActionEvent event) throws SQLException, ClassNotFoundException {

        NameErrorTextField.setText("");
        PriceErrorTextField.setText("");

        ResultSet res1 = p.select("drugs");
        ObservableList<String> res = FXCollections.observableArrayList();

        while(res1.next()){
            res.add(res1.getString("name"));
        }

        if(!res.contains(NameTextField.getText())){
            NameErrorTextField.setText("There is no such drug!");
            return;
        }else if(!isNumeric(PriceTextField.getText()) || PriceTextField.getText().isEmpty()){
            PriceErrorTextField.setText("Invalid Quantity");
            return;
        }

        p.update("price", PriceTextField.getText(), "name", NameTextField.getText());

        SuccessPriceChangeTextField1.setText("Successfully price changed!");
        System.out.println("Successfully price changed!");


    }

    @FXML
    void ClickRemoveButtonInSellButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        SellingDrugListTable.getItems().removeAll(SellingDrugListTable.getSelectionModel().getSelectedItem());

        total_price = 0;
        ResultSet res1;

        for(SellList n : SellingListTableValues){
            res1 = p.select("name", n.getName());

            while(res1.next()){
                double g = (1-(Integer.valueOf(n.getDiscount())/100.0));
                double s = res1.getInt("price") * g * Integer.valueOf(n.getQuantity());
                System.out.println(total_price);
                total_price = total_price + s;

            }
        }

        TotalCostLabelInSellAnchorpane.setText("Total cost: "+String.valueOf(total_price));
        System.out.println(total_price);

    }



    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        MiniWindow1.setVisible(false);
        windowChangePrice.setVisible(false);
        windowSell.setVisible(false);
        windowOrder.setVisible(false);
        windowHelp.setVisible(false);
        SoldWindow.setVisible(false);


        MiniWindow1.setVisible(true);
        searchDrug();
    }

    @FXML
    void ClickOnBackToAuthorization(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) BackToAuthorization.getScene().getWindow();
        stage1.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("2_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 510);
        stage.setTitle("Authorization");
        stage.setScene(scene);
        stage.show();


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
    void ClickOnChangePriceButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        MiniWindow1.setVisible(false);
        windowChangePrice.setVisible(false);
        windowSell.setVisible(false);
        windowOrder.setVisible(false);
        windowHelp.setVisible(false);
        SoldWindow.setVisible(false);


        windowChangePrice.setVisible(true);


    }

    @FXML
    void ClickOnHelpButton(ActionEvent event) {
        MiniWindow1.setVisible(false);
        windowChangePrice.setVisible(false);
        windowSell.setVisible(false);
        windowOrder.setVisible(false);
        windowHelp.setVisible(false);
        SoldWindow.setVisible(false);


        windowHelp.setVisible(true);

    }

    @FXML
    void ClickRemoveFromOrderedButtonInOrderAnchorpane(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if(OrderedListTableViewInOrderAnchorpane.getSelectionModel().getSelectedItem().getName().isEmpty()){
            return;
        }
        Connection conn = dbConnection.getConnected();

        String query = "UPDATE `dialogwindows` SET`value`= '" + OrderedListTableViewInOrderAnchorpane.getSelectionModel().getSelectedItem().getName() + "' WHERE `name` = 'dialog window 1'";
        PreparedStatement prs = conn.prepareStatement(query);
        prs.executeUpdate();
        prs.close();
        conn.close();

        Deliveryman d = new Deliveryman();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DialogWindowOfRemoveFromAlreadyOrderedList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 219, 144);
        stage.setTitle("Are you sure?");
        stage.setScene(scene);
        stage.show();



        ClickOnOrderDrugButton(new ActionEvent());

    }

    @FXML
    void ClickOnSoldButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        MiniWindow1.setVisible(false);
        windowChangePrice.setVisible(false);
        windowSell.setVisible(false);
        windowOrder.setVisible(false);
        windowHelp.setVisible(false);
        SoldWindow.setVisible(false);

        SoldWindow.setVisible(true);

        ResultSet res = p.select("sold");

        SoldListValues.clear();

        while(res.next()){
            SoldListValues.add(new SoldList(res.getString("name"), res.getString("quantity"), res.getString("price"), res.getString("discount"), res.getString("date"), res.getString("total")));
        }
        res.close();

        InSoldAnchopaneNameColumn.setCellValueFactory(new PropertyValueFactory<SoldList, String>("name"));
        InSoldAnchopaneQuantityColumn.setCellValueFactory(new PropertyValueFactory<SoldList, String>("quantity"));
        InSoldAnchorpanePriceColumn.setCellValueFactory(new PropertyValueFactory<SoldList, String>("price"));
        InSoldAnchopaneDiscountColumn.setCellValueFactory(new PropertyValueFactory<SoldList, String>("discount"));
        InSoldAnchopaneDateColumn.setCellValueFactory(new PropertyValueFactory<SoldList, String>("date"));
        InSoldAnchopaneTotalColumn.setCellValueFactory(new PropertyValueFactory<SoldList, String>("total"));

        InSoldAnchopaneSoldTableView.setItems(SoldListValues);


        FilteredList<SoldList> filteredData = new FilteredList<>(SoldListValues, b -> true);
        InSoldAnchopaneSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(SoldList -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (SoldList.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches type
            }else if (SoldList.getQuantity().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filtephappr matches madeby
            }else if (SoldList.getPrice().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches purpose
            }else if (SoldList.getDiscount().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches purpose
            }else if (SoldList.getDate().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches purpose
            }
            else return SoldList.getTotal().toLowerCase().contains(lowerCaseFilter);// Filter matches cost
        }));
        SortedList<SoldList> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(InSoldAnchopaneSoldTableView.comparatorProperty());
        InSoldAnchopaneSoldTableView.setItems(sortedData);

    }

    @FXML
    void ClickOnOrderDrugButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        MiniWindow1.setVisible(false);
        windowChangePrice.setVisible(false);
        windowSell.setVisible(false);
        windowOrder.setVisible(false);
        windowHelp.setVisible(false);
        SoldWindow.setVisible(false);


        windowOrder.setVisible(true);

        ResultSet res = p.select("for_deliveryman");
        ResultSet res2 = p.select("new_drug_for_delivery");

        OrderedListValues.clear();

        while(res.next()){
            OrderedListValues.add(new OrderedList(res.getString("name"), res.getString("quantity")));
        }

        while(res2.next()){
            OrderedListValues.add(new OrderedList(res2.getString("name"), res2.getString("quantity")));
        }

        NameOrderedListColumnInOrderAnchorpane.setCellValueFactory(new PropertyValueFactory<OrderedList, String>("name"));
        QuantityOrderedListColumnInOrderAnchorpane.setCellValueFactory(new PropertyValueFactory<OrderedList, String>("quantity"));

        OrderedListTableViewInOrderAnchorpane.setItems(OrderedListValues);




    }





    @FXML
    void ClickOnSearchDrugButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        MiniWindow1.setVisible(false);
        windowChangePrice.setVisible(false);
        windowSell.setVisible(false);
        windowOrder.setVisible(false);
        windowHelp.setVisible(false);
        SoldWindow.setVisible(false);


        MiniWindow1.setVisible(true);
        searchDrug();
    }

    @FXML
    void ClickOnSellButton(ActionEvent event) {
        MiniWindow1.setVisible(false);
        windowChangePrice.setVisible(false);
        windowSell.setVisible(false);
        windowOrder.setVisible(false);
        windowHelp.setVisible(false);
        SoldWindow.setVisible(false);


        windowSell.setVisible(true);

    }

    @FXML

    void searchAction(ActionEvent event) throws SQLException, ClassNotFoundException {
    }


    @FXML
    void searchDrug() throws SQLException, ClassNotFoundException {

        ResultSet res = p.select("drugs");
        tableValues.clear();

        ID.setCellValueFactory(new PropertyValueFactory<Drugs,String>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Drugs,String>("name"));
        Quantity.setCellValueFactory(new PropertyValueFactory<Drugs,String>("quantity"));
        Manufacturer.setCellValueFactory(new PropertyValueFactory<Drugs,String>("manufacturer"));
        Symptomatology.setCellValueFactory(new PropertyValueFactory<Drugs,String>("symptomatology"));
        Price.setCellValueFactory(new PropertyValueFactory<Drugs,String>("price"));
        Discount.setCellValueFactory(new PropertyValueFactory<Drugs,String>("discount"));

        while(res.next()){
            tableValues.add(new Drugs(res.getString("ID"), res.getString("name"), res.getString("quantity"), res.getString("manufacturer"), res.getString("symptomatology"), res.getString("price"), res.getString("discount")));
        }

        tableOfDrugs.setItems(tableValues);

        FilteredList<Drugs> filteredData = new FilteredList<>(tableValues, b -> true);
        SearchLabel.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Drugs -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (Drugs.getId().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches name
            } else if (Drugs.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches type
            }else if (Drugs.getQuantity().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filtephappr matches madeby
            }else if (Drugs.getManufacturer().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches purpose
            }
            else if (Drugs.getSymptomatology().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches purpose
            }else if (Drugs.getPrice().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches purpose
            }
                else return String.valueOf(Drugs.getDiscount()).contains(lowerCaseFilter);// Filter matches cost
        }));
        SortedList<Drugs> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableOfDrugs.comparatorProperty());
        tableOfDrugs.setItems(sortedData);
    }


    @FXML
    void ClickSellButtonInSellAnchorpane(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        for(SellList s : SellingListTableValues){
            p.sellFunc(s.getName(), s.getQuantity(), s.getDiscount());
        }

        SellingListTableValues.clear();
        SellingDrugListTable.setItems(SellingListTableValues);
        TotalCostLabelInSellAnchorpane.setText("Total cost:");


        System.out.println("Successfully sold!");

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PrintCheckWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 272, 148);
        stage.setTitle("Check");
        stage.setScene(scene);
        stage.show();




    }

}






