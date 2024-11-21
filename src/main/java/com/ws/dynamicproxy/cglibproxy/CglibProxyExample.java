package com.ws.dynamicproxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;

/**
 * Cglib代理示例
 * <p/>
 * cglib动态代理示例
 * <p/>
 * 原理：通过继承目标类并重写其方法实现动态代理
 *
 * @author wangsen
 * @date 2024/11/21
 */
public class CglibProxyExample {

    public static void main(String[] args) {
        // 创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealServiceTwo.class);
        enhancer.setCallback(new CglibProxyHandler());

        // 创建代理对象
        RealServiceTwo proxyObj = (RealServiceTwo) enhancer.create();

        // 通过代理对象执行方法
        proxyObj.doSomething();
    }


}
