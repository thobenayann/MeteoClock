module com.example.meteoclock {
    requires javafx.controls;
    requires javafx.fxml;
    requires unirest.java;
    requires json;

    opens com.example.meteoclock to javafx.fxml;
    exports com.example.meteoclock;
    exports com.example.meteoclock.controllers;
    opens com.example.meteoclock.controllers to javafx.fxml;
}