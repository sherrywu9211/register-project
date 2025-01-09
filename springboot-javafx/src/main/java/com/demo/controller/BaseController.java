package com.demo.controller;

import com.demo.websocket.WebSocketManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;

public class BaseController {

    // 共享資源
    protected static final String WEBSOCKET_URL = "ws://localhost:8081/ws";


    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    private StackPane contentArea; // 分頁顯示區域

    public void setContentPane(StackPane contentPane) {
        this.contentArea = contentPane;
    }

    // 產生分頁
    public void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource( "/static/views/" + fxmlFile));
            Pane page = loader.load(); // 加載並取得根節點

            contentArea.getChildren().clear(); // 清除 現有的子節點。
            contentArea.getChildren().add(page); // 將page 節點 加到 contentArea 。
//            // 檢查控制器類型 如果有子控制器，設置 contentPane
            if(loader.getController() instanceof BaseController) {
                ((BaseController) loader.getController()).setContentPane(contentArea);
            }

            // 檢查控制器類型，設置 contentPane
            Object controller = loader.getController();
            if (controller instanceof BaseController) {
                ((BaseController) controller).setContentPane(contentArea);
            }

        //     如果是 WebSocketClientViewController，初始化 WebSocketManager
            if (controller instanceof WebSocketClientViewController) {
                WebSocketClientViewController wsController = (WebSocketClientViewController) controller;
                WebSocketManager.getInstance().initialize(wsController);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    // 切換按鈕
    @FXML
    public void handleSwitchPage(ActionEvent event) {
        Button button = (Button) event.getSource(); //按鈕對象
        String targetPage = button.getUserData().toString();
        loadView(targetPage);
    }

}