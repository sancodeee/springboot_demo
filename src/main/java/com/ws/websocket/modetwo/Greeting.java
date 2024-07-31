package com.ws.websocket.modetwo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 服务端发送给客户端的消息类
 *
 * @author wangsen
 * @date 2024/07/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Greeting {

    /**
     * 内容
     */
    private String content;

}
