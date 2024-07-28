package com.ws.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 统一结果响应体
 *
 * @author wangsen_a
 * @date 2024/05/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private Boolean isSucceed = true;
    /**
     * 代码
     */
    private Integer code = 0;
    /**
     * 消息
     */
    private String message = "成功";
    /**
     * 数据
     */
    private T data;
    /**
     * 时间戳
     */
    private String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


    public static <T> Result<T> success() {
        Result<T> r = new Result<>();
        r.setCode(HttpStatus.OK.value());
        return r;
    }

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.setCode(HttpStatus.OK.value());
        r.setData(data);
        return r;
    }

    public static <T> Result<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> r = new Result<T>();
        r.setCode(HttpStatus.OK.value());
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static <T> Result<T> error() {
        Result<T> r = new Result<>();
        r.setIsSucceed(false);
        r.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setMessage("失败");
        return r;
    }

    public static <T> Result<T> error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static <T> Result<T> error(int code, String msg) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setIsSucceed(false);
        return r;
    }


}