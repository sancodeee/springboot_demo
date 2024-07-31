package com.ws.websocket.modetwo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 客户端发送给服务端的消息类
 *
 * @author wangsen
 * @date 2024/07/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HelloMessage {

    private String name;

}
