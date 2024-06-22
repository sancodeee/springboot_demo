package com.ws.common.aop;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.support.MultipartFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * 日志打印aop
 *
 * @author wangsen
 * @date 2024/05/19
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    /**
     * Before切入点
     */
    @Pointcut("@annotation(com.ws.common.annotation.BeforeLog)")
    public void beforePointcut() {
        // 占位方法，无实际意义
    }

    /**
     * Around切入点
     */
    @Pointcut("@annotation(com.ws.common.annotation.AroundLog)")
    public void aroundPointcut() {
        // 占位方法，无实际意义
    }


    /**
     * 记录请求日志的切面
     *
     * @param joinPoint 连接点
     */
    @Before("beforePointcut()")
    public void before(JoinPoint joinPoint) {
        try {
            addLog(joinPoint, "", 0);
        } catch (Exception e) {
            log.error("doBefore日志记录异常，异常信息为:", e);
        }
    }


    /**
     * 记录请求和响应日志的切面
     *
     * @param joinPoint 连接点
     * @return {@link Object }
     * @throws Throwable throwable
     */
    @Around("aroundPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录请求开始时间
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Exception e) {
            log.error("doAround日志记录异常，异常信息为:", e);
            throw e;
        }
        // 记录请求结束时间
        long endTime = System.currentTimeMillis();
        addLog(joinPoint, JSONUtil.toJsonStr(result), endTime - startTime);
        return result;
    }


    public void addLog(JoinPoint joinPoint, String outParams, long time) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.warn("Request attributes is null, unable to log request.");
            return;
        }

        HttpServletRequest request = attributes.getRequest();
        log.info("\n\r=========================================================================\n\r" +
                        "请求地址: {}\n\r" +
                        "请求方式: {}\n\r" +
                        "请求类方法: {}\n\r" +
                        "请求方法参数: {}\n\r" +
                        "返回报文: {}\n\r" +
                        "处理耗时: {}(ms)\n\r" +
                        "=========================================================================\n\r",
                request.getRequestURL(),
                request.getMethod(),
                joinPoint.getSignature(),
                JSONUtil.toJsonStr(filterArgs(joinPoint.getArgs())),
                outParams,
                time
        );
    }

    /**
     * 过滤参数
     *
     * @param args 参数列表
     * @return {@link List }<{@link Object }>
     */
    private List<Object> filterArgs(Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> !(arg instanceof HttpServletRequest || arg instanceof HttpServletResponse))
                .toList();
    }


}
