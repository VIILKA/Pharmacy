module com.example.pharmacy {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;

    requires java.sql;

    opens com.example.pharmacy to javafx.fxml;
    exports com.example.pharmacy;
}