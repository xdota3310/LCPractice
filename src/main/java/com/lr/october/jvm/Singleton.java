package com.lr.october.jvm;

/**
 * 通过 JVM 参数 -verbose:class 来打印类加载的先后顺序，并且在 LazyHolder 的初始化方法中打印特定字样
 *
 * @author shijie.xu
 * @since 2019年11月25日
 */
public class Singleton {
    private Singleton() {
    }

    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();

        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }

    public static Object getInstance(boolean flag) {
        if(flag) {
            return new LazyHolder[2];
        }
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        getInstance(true);
        System.out.println("----");
        getInstance(false);
    }
}
