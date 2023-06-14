module com.example.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.math3;
    requires org.apache.logging.log4j;

    requires org.apache.poi.poi;
    requires log4j;
    requires jbcrypt;


    opens com.example.application to javafx.fxml;
    exports com.example.application;
    exports com.example.application.controllers;
    opens com.example.application.controllers to javafx.fxml;
}