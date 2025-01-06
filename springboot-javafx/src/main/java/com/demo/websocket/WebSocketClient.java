package com.demo.websocket;

import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;

@ClientEndpoint
public class WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketClient.class);
    private Session currentSession;
    private WebSocketListener listener;

    public WebSocketClient(WebSocketListener listener) {
        this.listener = listener;
    }

    public void connect(String uri) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));
        } catch (Exception e) {
            logger.error("WebSocket 連線失敗: ", e);
            if (listener != null) listener.onError(e);
        }
    }

    public void sendMessage(String message) {
        if (currentSession != null && currentSession.isOpen()) {
            try {
                // 發送訊息至server端
                currentSession.getBasicRemote().sendText(message);
                logger.info("已發送訊息至server端：" + message);
            } catch (Exception e) {
                logger.error("發送訊息失敗: ", e);
                listener.onError(e);
            }
        } else {
            logger.warn("無法發送訊息: WebSocket 尚未連線");
        }
    }

    // WebSocket 成功連接時
    @OnOpen
    public void onOpen(Session session) {
        this.currentSession = session;
        listener.onConnected();
        logger.info("連線成功 已連接至Server端");
    }

    // 接收到伺服器訊息
    @OnMessage
    public void onMessage(String message) {
        listener.onMessageReceived(message);
        logger.info("收到server訊息: " + message);
    }

    // WebSocket 連線關閉
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        logger.info("連線已關閉: " + reason);
        listener.onDisconnected(reason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("連線失敗: ", throwable);
        listener.onError(throwable);
    }

}