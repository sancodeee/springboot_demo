package com.ws.websocket.modethree;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * WebSocket端点类
 *
 * @author wangsen
 * @date 2024/07/31
 */
@Slf4j
@ServerEndpoint("/ws/test3")
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session) {
        log.info("连接成功: {}", session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("收到消息: {}", message);
        // 服务器发送给客户端的消息
        session.getBasicRemote().sendText("已收到消息: {}" + message);
    }

    @OnClose
    public void onClose(Session session) {
        log.info("关闭连接: {}", session.getId());
    }

}
