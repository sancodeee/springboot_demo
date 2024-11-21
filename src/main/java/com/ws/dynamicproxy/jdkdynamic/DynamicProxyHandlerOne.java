package com.ws.dynamicproxy.jdkdynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理类
 *
 * @author wangsen
 * @date 2024/11/21
 */
public class DynamicProxyHandlerOne implements InvocationHandler {

    // 目标对象(被代理的对象)
    private final Object target;

    public DynamicProxyHandlerOne(RealServiceOne realServiceOne) {
        this.target = realServiceOne;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理，方法执行前----");
        // 将代理对象和方法参数传入
        Object result = method.invoke(target, args);
        System.out.println("jdk动态代理，方法执行后----");
        return result;
    }

}
