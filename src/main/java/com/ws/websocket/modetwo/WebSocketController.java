package com.ws.websocket.modetwo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * websocket控制器
 *
 * @author wangsen
 * @date 2024/07/31
 */
@Controller
public class WebSocketController {

    /**
     * 处理客户端发送的消息
     * </p>
     * 基于STOMP协议的WebSocket控制器
     *
     * @param message 消息
     * @return {@link Greeting}
     * @throws Exception 异常
     */
    @MessageMapping("/hello") // 客户端发送到服务端的路径
    @SendTo("/topic/greetings") // 服务端发送到客户端的路径
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
