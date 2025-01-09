package com.demo.websocket;

import com.demo.controller.WebSocketClientViewController;
import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketClient.class);
    private Session currentSession;
    private WebSocketClientViewController webSocketClientViewController;

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setWebSocketClientViewController(WebSocketClientViewController controller) {
        this.webSocketClientViewController = controller;
    }

    public void connect(String uri) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));
            logger.info("WebSocket 連線成功");
        } catch (Exception e) {
            webSocketClientViewController.onErrorText(e); // 回報錯誤
            logger.error("WebSocket 連線失敗: ", e);
        }
    }

    // WebSocket 成功連接
    @OnOpen
    public void onOpen(Session session) {
        this.currentSession = session;
        logger.info("已連接至Server端，session: " + currentSession);
        if (webSocketClientViewController != null) {
            webSocketClientViewController.onConnectedText();
        }
        // 啟動心跳機制 檢查Server端是否持續啟動
        WebSocketManager.getInstance().startHeartbeat();
        logger.info("啟動心跳機制，每五秒發送一次");
    }

    // 接收到伺服器訊息
    @OnMessage
    public void onMessage(String message) {
        if(webSocketClientViewController != null) {
            webSocketClientViewController.onMessageReceivedText(message);
        }
        logger.info("收到server訊息: " + message);
    }

    // WebSocket 連線關閉
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        if(webSocketClientViewController != null) {
            webSocketClientViewController.onCloseText(reason);
        }
        WebSocketManager.getInstance().closeSession(currentSession);
        logger.info("連線已關閉: " + reason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        logger.error("連線失敗: ", throwable);
        webSocketClientViewController.onErrorText(throwable);
    }

    public boolean sendMessage(String message) {
        if (currentSession != null && currentSession.isOpen()) {
            try {
                // 發送訊息至server端
                currentSession.getBasicRemote().sendText(message);
                logger.info("已發送訊息至server端：" + message);
                return true;
            } catch (Exception e) {
                logger.error("發送訊息失敗: ", e);
                webSocketClientViewController.onErrorText(e);
                return false;
            }
        } else {
            logger.warn("無法發送訊息: WebSocket 尚未連線");
            return false;
        }
    }

}