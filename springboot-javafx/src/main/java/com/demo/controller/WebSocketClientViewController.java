package com.demo.controller;

import com.demo.websocket.WebSocketClient;
import com.demo.websocket.WebSocketListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Timer;
import java.util.TimerTask;
import static com.demo.util.showErrorUtil.showErrorAlert;

//public class WebSocketClientViewController {
public class WebSocketClientViewController implements WebSocketListener {

    private static final String WEBSOCKET_URL = "ws://localhost:8081/ws";
    private WebSocketClient webSocketClient;
    private int retryCount = 0;

    @FXML
    private Label connectStatusLabel;
    @FXML
    private TextField sendMsgText;
    @FXML
    private Label receiveMsgText;
    @FXML
    private Label errorLabel;

    // 初始化 WebSocketClient
    public void initWebSocketClient() {
//        webSocketClient = new WebSocketClient(this);
//        webSocketClient.connect(WEBSOCKET_URL);

        // 從 MainViewController 獲取共享的 WebSocketClient
        webSocketClient = MainViewController.getWebSocketClient();
    }

    @FXML
    public void submitButtonClick() {
        String sendMsg = sendMsgText.getText();
        if (sendMsg.isEmpty()) {
            errorLabel.setText("訊息不可為空！");
            return;
        }
        webSocketClient.sendMessage(sendMsg);
        errorLabel.setText("訊息發送成功！");
        sendMsgText.clear();
    }

    @Override
    public void onConnected() {
        Platform.runLater(() -> connectStatusLabel.setText("連線成功"));
    }

    @Override
    public void onMessageReceived(String message) {
        Platform.runLater(() -> receiveMsgText.setText(message));
    }

    @Override
    public void onDisconnected(Object reason) {
        Platform.runLater(() -> connectStatusLabel.setText("連線關閉"));
    }

    @Override
    public void onError(Throwable throwable) {
        retryCount++;
        Platform.runLater(() -> {
            if (retryCount < 5) { // 最大重連次數
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() ->webSocketClient.connect(WEBSOCKET_URL));
                    }
                }, 2000); // 延遲 2000 毫秒（2 秒）
                connectStatusLabel.setText("連線中...（重試次數：" + retryCount + "）");
            } else {
                connectStatusLabel.setText("連線失敗");
                errorLabel.setText("WebSocket 發生錯誤: " + throwable.getMessage());
                showErrorAlert("WebSocket 無法連線，請稍後再試。");
            }
        });
    }


}