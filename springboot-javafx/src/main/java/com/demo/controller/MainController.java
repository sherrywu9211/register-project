package com.demo.controller;

import com.demo.model.EmergencyResponse;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class MainController extends BaseController {

    @FXML
    private StackPane contentArea; // 分頁顯示區域

    @FXML
    public void initialize() {
        setContentPane(contentArea);
//        loadView("main.fxml");
    }

}