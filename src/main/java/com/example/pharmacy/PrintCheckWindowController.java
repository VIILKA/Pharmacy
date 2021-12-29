
package com.example.pharmacy;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


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
    void ClickYesPrintCheckButton(ActionEvent event) throws IOException, DocumentException {
            String file_name = "D:\\Cheks\\drugs.pdf";
            Document document = new Document();
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd/___HH/mm/ss").format(Calendar.getInstance().getTime());
        PdfWriter.getInstance (document, new FileOutputStream(file_name));

        document.open();

        document.add(Image.getInstance("D:\\Pharmacy_git_1\\src\\main\\resources\\com\\example\\pharmacy\\logocheck.png"));
        Paragraph x0 = new Paragraph("----OOO *Pharmacy*----  ");
        Paragraph x1 = new Paragraph(timeStamp + "----SYS Administrator");
        Paragraph x2 = new Paragraph("Selling:");
        Paragraph x3 = new Paragraph(" ");
        Paragraph x4 = new Paragraph("Total: ");
        Paragraph x5 = new Paragraph("_________________________________  ");
        document.add(x0);
        document.add(x1);
        document.add(x2);
        document.add(x3);
        document.add(x4);
        document.add(x5);
        document.close();
    }
}







