package com.ws.designpattern.factory.factorymethod;

/**
 * 猫动物
 *
 * @author wangsen
 * @date 2024/08/10
 */
public class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("喵，喵，喵");
    }
}
