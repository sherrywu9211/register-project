package com.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController extends BaseController {
//    private static final String MNR_REGISTER_VIEW = "/static/views/mnrRegister.fxml";
//    private static final String API_CHANGE_VIEW = "/static/views/apiChange.fxml";

    @FXML
    private StackPane contentArea; // 分頁顯示區域

    @FXML
    public void initialize() {
        setContentPane(contentArea);
//        // 確保 contentArea 已正確初始化
//        if (contentArea == null) {
//            System.err.println("-----Error: contentArea is not initialized!");
//        }
//        loadView("main.fxml");
    }

//
//    // MNR註冊查詢
//    @FXML
//    public void onMnrButtonClick() {
//        loadView(MNR_REGISTER_VIEW);
//    }
//
//    // API接收服務
//    @FXML
//    public void onApiButtonClick() {
//        loadView(API_CHANGE_VIEW);
//    }

//    // 分頁加載方法
//    public void loadView(String fxmlFile) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
//            Parent page = loader.load(); // 加載並取得根節點
//            contentArea.getChildren().setAll(page); // 清空並替換 contentArea
//        } catch (IOException e) {
//            System.err.println("-----Error loading FXML:----- " + fxmlFile);
//            e.printStackTrace();
//        }
//    }
}