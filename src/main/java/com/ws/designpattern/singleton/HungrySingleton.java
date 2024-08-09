package com.ws.designpattern.singleton;

/**
 * 饥饿单例
 *
 * @author wangsen
 * @date 2024/08/10
 */
public class HungrySingleton {
    // 在类加载时，就实例化对象
    private final static HungrySingleton INSTANCE = new HungrySingleton();

    // 防止外部new对象
    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

}
