module com.example.martintodorovemployees {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.martintodorovemployees.app to javafx.fxml;
    opens com.example.martintodorovemployees.controller to javafx.fxml;
    exports com.example.martintodorovemployees.app;
    exports com.example.martintodorovemployees.controller;
    exports com.example.martintodorovemployees.model;
    exports com.example.martintodorovemployees.service;
}