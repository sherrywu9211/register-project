package com.demo.controller;

import com.demo.websocket.WebSocketManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Timer;
import java.util.TimerTask;
import static com.demo.util.showErrorUtil.showErrorAlert;

public class WebSocketClientViewController extends BaseController {

    @FXML
    private Label connectStatusLabel;
    @FXML
    private TextField sendMsgText;
    @FXML
    private Label receiveMsgText;
    @FXML
    private Label errorLabel;

    @FXML
    public void submitButtonClick() {
        String sendMsg = sendMsgText.getText();
        if (sendMsg.isEmpty()) {
            errorLabel.setText("訊息不可為空！");
            return;
        }
        // 發送訊息
        boolean sendResult = WebSocketManager.getInstance().getWebSocketClient().sendMessage(sendMsg);
        errorLabel.setText(sendResult ? "訊息發送成功！" : "訊息發送失敗！");
        sendMsgText.clear();
    }

    public void onConnectedText() {
        Platform.runLater(() -> connectStatusLabel.setText("連線成功"));
    }

    public void onMessageReceivedText(String message) {
        Platform.runLater(() -> receiveMsgText.setText(message));
    }

    public void onCloseText(Object reason) {
        Platform.runLater(() -> connectStatusLabel.setText("連線關閉"));
    }

    private int retryCount = 0;
    public void onErrorText(Throwable throwable) {
        retryCount++;
        Platform.runLater(() -> {
            if (retryCount < 5) { // 最大重連次數
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        WebSocketManager.getInstance().getWebSocketClient().connect(WEBSOCKET_URL);
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