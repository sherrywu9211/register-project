package com.demo.websocket;

import com.demo.controller.BaseController;
import com.demo.controller.WebSocketClientViewController;
import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.demo.util.ProgramCheckerUtil.startWebsocketProjectServer;

public class WebSocketManager extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketManager.class);
    private static WebSocketManager instance;
    private final WebSocketClient webSocketClient;
    private boolean isConnected = false;

    private WebSocketManager() {
        webSocketClient = new WebSocketClient();
    }

    // 確保WebSocketManager只有一個
    public static WebSocketManager getInstance() {
        if (instance == null) {
            instance = new WebSocketManager();
        }
        return instance;
    }

    // init時 websocket連線
    public void initialize(WebSocketClientViewController webSocketClientViewController) {
        // 確保 WebSocketClient 只被初始化一次
//        if (!isConnected) {
//            if (webSocketClientViewController != null) {
        webSocketClient.setWebSocketClientViewController(webSocketClientViewController);
//            }
        webSocketClient.connect(WEBSOCKET_URL);
        isConnected = true; // 標記已連線
        logger.info("WebSocketManager initialize finish");

//        }else {
////            // 即使已連線，重新設定控制器
//            webSocketClient.setWebSocketClientViewController(webSocketClientViewController);
//            logger.info("WebSocketManager reinitialized with new controller");
//        }
    }


    public WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    public boolean isConnected() {
        return isConnected;
    }

    //------------------------------------------------------------------------------------
    // 心跳機制
    private static Timer heartbeatTimer;
    private int missedHeartbeats = 0;
    private static final int MAX_MISSED_HEARTBEATS = 3; // 最多允許 3 次未回應

    public void startHeartbeat() {
        heartbeatTimer = new Timer(true);
        heartbeatTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                performHeartbeatCheck(webSocketClient);
            }
        }, 0, 5000); // 每 5 秒發送一次
    }

    public void performHeartbeatCheck(WebSocketClient webSocketClient1) {
        Session currentSession = webSocketClient1.getCurrentSession();
        if (currentSession != null && currentSession.isOpen()) {
            try {
                currentSession.getBasicRemote().sendText("ping"); // 發送心跳訊息
                logger.info("發送心跳訊息：ping");
            } catch (IOException e) {
                logger.error("心跳訊息發送失敗: ", e);
            }
        } else {
            missedHeartbeats++;
            logger.warn("missedHeartbeat次數： "+ missedHeartbeats);
            if (missedHeartbeats >= MAX_MISSED_HEARTBEATS) {
                missedHeartbeats = 0;
                updateSession(currentSession);
            }
        }
    }

    public void updateSession(Session currentSession) {
        closeSession(currentSession);
        if (startWebsocketProjectServer()) {
            webSocketClient.connect(WEBSOCKET_URL);
            currentSession = webSocketClient.getCurrentSession();
            logger.info("更新 Session： " + currentSession);
        } else {
            heartbeatTimer.cancel();
            heartbeatTimer = null;
            logger.info("已關閉心跳機制");
        }
    }

    public void closeSession(Session currentSession) {
        if (currentSession != null) {
            try {
                currentSession.close();
                logger.info("關閉 session: " + currentSession);
            } catch (IOException e) {
                logger.error("關閉 session失敗: ", e);
            }
        }
    }

}