package com.ws.dynamicproxy.jdkdynamic;

import java.lang.reflect.Proxy;

/**
 * JDK dynamic代理示例
 * <p/>
 * jdk动态代理必须基于接口实现
 * <p/>
 * 原理：通过使用java的Proxy类动态生成代理对象
 *
 * @author wangsen
 * @date 2024/11/21
 */
public class JDKDynamicProxyExample {
    public static void main(String[] args) {

        // 创建一个被代理的对象
        RealServiceOne realServiceOne = new RealServiceOne();

        // 通过生成代理对象去代理目标对象执行其内部方法
        ServiceOne proxyInstance = (ServiceOne) Proxy.newProxyInstance(realServiceOne.getClass().getClassLoader(),
                realServiceOne.getClass().getInterfaces(),
                new DynamicProxyHandlerOne(realServiceOne));

        // 代理对象代替原对象执行其内部方法
        proxyInstance.doSomething();

    }
}
