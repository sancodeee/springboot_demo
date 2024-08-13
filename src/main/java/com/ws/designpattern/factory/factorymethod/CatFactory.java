package com.ws.designpattern.factory.factorymethod;

/**
 * 猫工厂
 *
 * @author wangsen
 * @date 2024/08/10
 */
public class CatFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}
