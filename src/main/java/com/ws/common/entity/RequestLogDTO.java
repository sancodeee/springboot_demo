package com.ws.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求日志dto
 *
 * @author wangsen
 * @date 2024/05/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogDTO {

    /**
     * url
     */
    private String url;

    /**
     * ip
     */
    private String ip;

    /**
     * 类方法
     */
    private String classMethod;

    /**
     * 参数列表
     */
    private Object[] args;

}
