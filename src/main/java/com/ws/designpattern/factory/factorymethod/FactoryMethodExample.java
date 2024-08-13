package com.ws.designpattern.factory.factorymethod;

/**
 * 工厂方法示例
 *
 * @author wangsen
 * @date 2024/08/10
 */
public class FactoryMethodExample {

    public static void main(String[] args) {
        DogFactory dogFactory = new DogFactory();
        Animal dog = dogFactory.createAnimal();
        dog.speak();

        CatFactory catFactory = new CatFactory();
        Animal cat = catFactory.createAnimal();
        cat.speak();

    }

}
        