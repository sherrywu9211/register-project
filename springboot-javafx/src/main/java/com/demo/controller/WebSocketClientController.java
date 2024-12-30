package com.demo.controller;

import jakarta.websocket.*;

@ClientEndpoint
public class WebSocketClientController {


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("已連接到伺服器");
        try {
            session.getBasicRemote().sendText("Hello from 客戶端");
        } catch (Exception e) {
            e.printStackTrace();
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
