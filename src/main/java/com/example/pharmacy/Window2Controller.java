package com.example.pharmacy;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;

public class Window2Controller implements Initializable {
    ObservableList<String> choiceList = FXCollections.observableArrayList("Pharmacist", "Deliveryman");
    Authoziration au = new Authoziration();

    @FXML
    private Button enterButton1;

    @FXML
    private ImageView zxc;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label lognLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label showLabel;

    @FXML
    private Button signIn;

    @FXML
    private AnchorPane windowSignIn;

//    @FXML
//
//    void initialize(){
//    }


    @FXML
    void SignInButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        ResultSet res1 = au.select("authorization", "login", loginTextField.getText());

        String accT = "1";

        while(res1.next()){
            accT = res1.getString("ID_accType");
        }


        String login = null, password = null;
        ResultSet res = au.select("authorization", "ID_accType", accT);
        while(res.next()){
            login = res.getString("login");
            password = res.getString("password");
        }
        if(loginTextField.getText().equals(login)){
            if(passwordTextField.getText().equals(password)){
                if(accT.equals("1")){
                    Stage stage1 = (Stage) signIn.getScene().getWindow();
                    stage1.close();

                     Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PharmacistAccount.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 510);
                    stage.setTitle("Pharmacist");
                    stage.setScene(scene);
                    stage.show();





                }
                else if(accT.equals("2")){
                    Stage stage1 = (Stage) signIn.getScene().getWindow();
                    stage1.close();

                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DeliverymanAccount.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 510);
                    stage.setTitle("Deliveryman");
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("menu of Deliveryman");
                }
            }else{

                passwordLabel.setText("Invalid password");
            }
        }else{
            lognLabel.setText("Invalid login");
        }
    }

    @FXML
    void clickOnEnterButtonAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(zxc);
        rotate.setDuration(Duration.millis(5000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();


    }
}










