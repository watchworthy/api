package com.watchworthy.api.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> webSocketSessionList = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) throws Exception{
        webSocketSessionList.add(session);
    }

    @Override
    protected void handleTextMessage(@NotNull WebSocketSession session, @NotNull TextMessage message) throws Exception{
        for(WebSocketSession webSocketSession: webSocketSessionList){
            webSocketSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(@NotNull WebSocketSession session, @NotNull CloseStatus status) throws Exception{
        webSocketSessionList.remove(session);
    }
}
