package com.ws.dynamicproxy.cglibproxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib代理处理器
 *
 * @author wangsen
 * @date 2024/11/21
 */
public class CglibProxyHandler implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib动态代理，方法执行前-------");

        Object result = proxy.invokeSuper(obj, args);

        System.out.println("cglib动态代理，方法执行后-------");
        return result;
    }

}
