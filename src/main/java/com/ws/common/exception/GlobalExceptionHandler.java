package com.ws.common.exception;

import com.ws.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理程序
 *
 * @author wangsen
 * @date 2024/05/19
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常处理程序
     *
     * @param e e
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        log.error("异常信息：", e);
        return Result.error("失败");
    }

    /**
     * 自定义处理程序
     *
     * @param e e
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(CustomException.class)
    public Result<?> customHandler(CustomException e) {
        //返回错误信息
        log.error("异常信息：", e);
        return Result.error(e.getCode(), e.getMsg());
    }

}
