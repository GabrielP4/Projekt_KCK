module com.example.projekt_60134_kck {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javax.mail.api;


    opens com.example.projekt_60134_kck to javafx.fxml;
    exports com.example.projekt_60134_kck;
}