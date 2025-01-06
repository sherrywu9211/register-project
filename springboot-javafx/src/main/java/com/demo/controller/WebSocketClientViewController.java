package com.demo.controller;

import com.demo.websocket.WebSocketClient;
import com.demo.websocket.WebSocketListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WebSocketClientViewController implements WebSocketListener {

    private WebSocketClient webSocketClient;
	private static final int MAX_RETRIES = 5;
    private int retryCount = 0;

    @FXML
    private Label connectStatusLabel;
    @FXML
    private TextField sendMsgText;
    @FXML
    private Label receiveMsgText;
    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        webSocketClient = new WebSocketClient(this);
        connectWebSocket();
    }

    private void connectWebSocket() {
        webSocketClient.connect("ws://localhost:8081/ws");
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
            if (retryCount < MAX_RETRIES) {
                connectStatusLabel.setText("連線中...（重試次數：" + retryCount + "）");
                connectWebSocket();
            } else {
                connectStatusLabel.setText("連線失敗");
                errorLabel.setText("WebSocket 發生錯誤: " + throwable.getMessage());
                showErrorAlert("WebSocket 無法連線，請稍後再試。");
            }
        });
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("錯誤");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}