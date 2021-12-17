
package com.example.pharmacy;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.stage.Stage;

public class PrintCheckWindowController {

    @FXML
    private Button PrintCheckYesButton;

    @FXML
    private Button PrintNoCheckButton;

    @FXML
    void ClickNoCheckButton(ActionEvent event) {
        Stage stage1 = (Stage) PrintNoCheckButton.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void ClickYesPrintChechButton(ActionEvent event) {
        Pharmacist p = new Pharmacist();

        //make make check function


    }

}

