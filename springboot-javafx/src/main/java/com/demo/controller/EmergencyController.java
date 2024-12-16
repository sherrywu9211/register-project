package com.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmergencyController {

    @FXML
    private Label apiResult;

    @FXML
    protected void onApiButtonClick() {
        apiResult.setText("api還沒開發完~~~~");
    }
}
