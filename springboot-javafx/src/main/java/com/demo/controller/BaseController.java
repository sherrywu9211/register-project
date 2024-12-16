package com.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class BaseController {

    private final String basePath = "/static/views/";

    private StackPane contentArea; // 分頁顯示區域
    public void setContentPane(StackPane contentPane) {
        this.contentArea = contentPane;
    }

    // 分頁加載方法
    public void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource( basePath + fxmlFile));
            Pane page = loader.load(); // 加載並取得根節點
            contentArea.getChildren().clear(); // 清除 現有的子節點。
            contentArea.getChildren().add(page); // 將page 節點 加到 contentArea 。
            if(loader.getController() instanceof BaseController) {  //檢查控制器類型
                ((BaseController) loader.getController()).setContentPane(contentArea);
            }
        } catch (IOException e) {
            System.err.println("-----Error loading FXML:----- " + fxmlFile);
            e.printStackTrace();
        }
    }

    // 按鈕切換
    @FXML
    public void handleSwitchPage(ActionEvent event) {
        Button button = (Button) event.getSource(); //按鈕對象
        String targetPage = button.getUserData().toString();
        loadView(targetPage);
    }
}
