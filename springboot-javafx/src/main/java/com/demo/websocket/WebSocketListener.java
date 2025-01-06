package com.demo.websocket;

public interface WebSocketListener {

    void onConnected();
    void onMessageReceived(String message);
    void onDisconnected(Object reason);
    void onError(Throwable throwable);

}