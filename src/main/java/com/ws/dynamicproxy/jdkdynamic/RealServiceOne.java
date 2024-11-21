package com.ws.dynamicproxy.jdkdynamic;

/**
 * 服务一实现类
 *
 * @author wangsen
 * @date 2024/11/21
 */
public class RealServiceOne implements ServiceOne {

    @Override
    public void doSomething() {
        System.out.println("---------服务一实现类执行了-------");
    }

}
