package com.ws.designpattern.factory.factorymethod;

/**
 * 狗工厂
 *
 * @author wangsen
 * @date 2024/08/10
 */
public class DogFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}
