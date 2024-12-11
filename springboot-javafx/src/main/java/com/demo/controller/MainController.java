package com.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {
    private static final String MNR_REGISTER_VIEW = "/static/views/mnrRegister.fxml";
    private static final String API_CHANGE_VIEW = "/static/views/apiChange.fxml";

    @FXML
    private StackPane contentArea; // 分頁顯示區域

    // MNR註冊查詢
    @FXML
    protected void onMnrButtonClick() {
        loadView(MNR_REGISTER_VIEW);
    }

    // API接收服務
    @FXML
    protected void onApiButtonClick() {
        loadView(API_CHANGE_VIEW);
    }

    // 分頁加載方法
    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node page = loader.load();
            contentArea.getChildren().setAll(page); // 清除舊內容並載入新內容
        } catch (IOException e) {
            System.err.println("-----Error loading FXML:----- " + fxmlFile);
            e.printStackTrace();
        }
    }
}
