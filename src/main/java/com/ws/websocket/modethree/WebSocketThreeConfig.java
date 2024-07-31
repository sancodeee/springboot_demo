package com.ws.websocket.modethree;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置
 *
 * @author wangsen
 * @date 2024/07/31
 */
@Configuration
public class WebSocketThreeConfig {

    /**
     * 这种方式主要使用Java标准的WebSocket API，并且在Spring Boot中通过配置 ServerEndpointExporter 来自动注册端点。
     *
     * @return {@link ServerEndpointExporter }
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
