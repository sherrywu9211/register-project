package com.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class MainViewController extends BaseController {

    @FXML
    private StackPane contentArea; // 分頁顯示區域

    @FXML
    public void initialize() {
        setContentPane(contentArea);
    }

}