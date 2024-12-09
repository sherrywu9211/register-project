package com.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PostController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("[controllerText] Welcome to JavaFX Application!");
    }
}
