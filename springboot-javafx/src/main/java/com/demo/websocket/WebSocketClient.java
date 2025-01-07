package com.demo.websocket;

import com.demo.controller.WebSocketClientViewController;
import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;
import static com.demo.util.ProgramCheckerUtil.startWebsocketProjectServer;

@ClientEndpoint
public class WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketClient.class);
    private Session currentSession;
    private WebSocketListener listener;
    private WebSocketClientViewController webSocketClientViewController;
    public WebSocketClient(WebSocketListener listener) {
        this.listener = listener;
    }

    // WebSocket 成功連接時
    @OnOpen
    public void onOpen(Session session) {
        this.currentSession = session;
//        if (listener != null) {
//            listener.onConnected();
//        }
        listener.onConnected();
        System.out.println( "------onOpen------------------"+ currentSession);
        logger.info("連線成功 已連接至Server端");

        // 啟動心跳機制 檢查Server端是否持續啟動
//        startHeartbeat();
    }

    // 接收到伺服器訊息
    @OnMessage
    public void onMessage(String message) {
        System.out.println( "------onMessage------------------"+ currentSession);

        listener.onMessageReceived(message);
        logger.info("收到server訊息: " + message);
    }

    // WebSocket 連線關閉
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println( "------onClose------------------"+ currentSession);

        logger.info("連線已關閉: " + reason);
        listener.onDisconnected(reason);
//        stopHeartbeat();
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        System.out.println( "------onError------------------"+ currentSession);
        logger.error("連線失敗: ", throwable);
        listener.onError(throwable);
    }

    public void connect(String uri) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));
        } catch (Exception e) {
            try {
                currentSession.close();
            }catch (IOException e1) {
                logger.error(e1.getMessage(), e1);
            }
            logger.error("WebSocket 連線失敗: ", e);
//            if (controller != null) controller.onError(e); // 回報錯誤
            if (listener != null) listener.onError(e);
        }
    }

    public void sendMessage(String message) {
        System.out.println( "-----------sendMessage-------------"+ currentSession);
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

    public void close() {
        stopHeartbeat();
        if (currentSession != null) {
            try {
                currentSession.close();
            } catch (IOException e) {
                logger.error("關閉 WebSocket 連線失敗", e);
            }
        }
    }
    private Timer heartbeatTimer;
    private static final int MAX_MISSED_HEARTBEATS = 3; // 最多允許 3 次未回應
    private int missedHeartbeats = 0;

    public void startHeartbeat(){
        heartbeatTimer = new Timer(true); // Daemon thread
        heartbeatTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("--------currentSession---------"+currentSession);
                if (currentSession != null && currentSession.isOpen()) {
                    try {
                        currentSession.getBasicRemote().sendText("ping"); // 發送心跳訊息
                        logger.info("發送心跳訊息：ping");
                    } catch (IOException e) {
                        logger.error("心跳訊息發送失敗", e);
                    }
                } else {
                    missedHeartbeats++;
                    if (missedHeartbeats >= MAX_MISSED_HEARTBEATS) {
                        logger.warn("超過最大心跳失敗次數，認定伺服器已關閉");
                        startWebsocketProjectServer();
//                        heartbeatTimer.cancel();
                    }
                }
            }
        }, 0, 5000); // 每 5 秒發送一次
    }
//    private void startHeartbeat() {
//        if (heartbeatTimer != null) {
//            heartbeatTimer.cancel();
//        }
//        heartbeatTimer = new Timer(true);
//        heartbeatTimer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                if (currentSession != null && currentSession.isOpen()) {
//                    try {
//                        currentSession.getBasicRemote().sendText("ping");
//                    } catch (IOException e) {
//                        logger.error("心跳發送失敗", e);
//                    }
//                }
//            }
//        }, 0, 5000);
//    }

    private void stopHeartbeat() {
        if (heartbeatTimer != null) {
            heartbeatTimer.cancel();
            heartbeatTimer = null;
        }
    }
}