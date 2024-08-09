package com.ws.designpattern.singleton;

/**
 * 单例
 *
 * @author wangsen
 * @date 2024/08/10
 */
public class LazySingleton {
    // 构造方法私有 防止外部new对象
    private LazySingleton() {
    }

    // 懒加载
    private volatile static LazySingleton instance = null;

    private LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }


}
