package com.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController extends BaseController {

    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {
        setContentPane(contentArea);
//        可指定起始頁面
//        loadPage("mnrRegister.fxml");
    }

}