package com.ws.designpattern.factory.factorymethod;

/**
 * 动物工厂
 *
 * @author wangsen
 * @date 2024/08/10
 */
public interface AnimalFactory {

    /**
     * 创建动物
     * <p> 返回一个抽象类型
     *
     * @return {@link Animal }
     */
    public Animal createAnimal();

}
