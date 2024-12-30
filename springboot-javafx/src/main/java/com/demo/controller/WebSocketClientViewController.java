package com.demo.controller;

import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientEndpoint
public class WebSocketClientViewController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketClientViewController.class);

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("已連接到B專案");
        try {
            session.getBasicRemote().sendText("我是A專案");
        } catch (Exception e) {
            logger.error( "---onOpen()-連線失敗--" + e.getMessage());
        }
    }
    @OnMessage
    public void onMessage(String message) {
        System.out.println("收到伺服器訊息: " + message);
    }
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("連線已關閉: " + closeReason);
    }

}
