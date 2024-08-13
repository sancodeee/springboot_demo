package com.ws.designpattern.factory.factorymethod;

/**
 * 狗动物
 *
 * @author wangsen
 * @date 2024/08/10
 */
public class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("汪，汪，汪");
    }
}
