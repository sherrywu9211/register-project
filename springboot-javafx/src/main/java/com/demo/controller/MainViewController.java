package com.demo.controller;

import com.demo.websocket.WebSocketClient;
import com.demo.websocket.WebSocketListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MainViewController extends BaseController implements WebSocketListener {
//    private WebSocketClient webSocketClient;
    private static final String WEBSOCKET_URL = "ws://localhost:8081/ws";
//    WebSocketListener webSocketListener;
    private static WebSocketClient webSocketClient; // 靜態共享的 WebSocketClient

    @FXML
    private StackPane contentArea; // 分頁顯示區域

    @FXML
    public void initialize() {
        setContentPane(contentArea);
//        webSocketClient = new WebSocketClient(webSocketListener);
//        webSocketClient.connect(WEBSOCKET_URL);

        // 確保 WebSocketClient 只被初始化一次
        if (webSocketClient == null) {
            webSocketClient = new WebSocketClient(this); // 傳遞當前控制器作為 Listener
            webSocketClient.connect(WEBSOCKET_URL);
        }
    }
    // 提供 WebSocketClient 給其他控制器
    public static WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    @FXML
    private Label connectStatusLabel;

    @Override
    public void onConnected() {
//        Platform.runLater(() -> connectStatusLabel.setText("連線成功"));
    }

    @Override
    public void onMessageReceived(String message) {

    }

    @Override
    public void onDisconnected(Object reason) {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}