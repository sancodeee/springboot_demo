package com.ws.websocket.modeone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * webSocket处理器
 *
 * @author wangsen
 * @date 2024/07/31
 */
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    /**
     * 连接建立后
     *
     * @param session 会话
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("连接成功:{}", session.getId());
    }

    /**
     * 处理短信
     *
     * @param session 会话
     * @param message 消息
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        log.info("收到消息:{}", payload);
        // session方法用于服务端给客户端发送消息
        session.sendMessage(new TextMessage("echo: 已收到：{}" + payload));
    }

    /**
     * 连接关闭后
     *
     * @param session 会话
     * @param status  状态
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("连接关闭:{}", session.getId());
    }

}
