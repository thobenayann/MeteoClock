package com.example.meteoclock.controllers;

import com.example.meteoclock.models.JsonConsumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MeteoClockController implements Initializable {
    @FXML
    private TextField city;

    @FXML
    private ImageView image;

    @FXML
    private Label ville;

    @FXML
    private Label temps;

    @FXML
    private Label temperature;

    @FXML
    private Label error;

    private JsonConsumer cons = new JsonConsumer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doRequest();
    }

    public void doRequest() {
        String search;
        // Give default value to city displayed
        if (Objects.equals(city.getText(), "")) {
            search = "Paris";
        } else {
            search = city.getText();
        }
        clearInterface();
        try {
            cons.sendRequest(search);

            temperature.setText(cons.getTemperature());
            temps.setText(cons.getTemps());
            ville.setText(cons.getVille());

            image.setImage(new Image(cons.getIcon()));
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "***" + e.getMessage());
            error.setText(e.getMessage());
        }
    }

    private void clearInterface() {
        city.setText("");
        ville.setText("");
        temps.setText("");
        temperature.setText("");
        error.setText("");
        image.setImage(null);
    }

    public void onEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            doRequest();
        }
    }
}