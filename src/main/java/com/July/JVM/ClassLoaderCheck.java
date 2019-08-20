package com.July.JVM;

/**
 * 自定义加载器检测
 *
 * @author shijie.xu
 * @since 2019年07月29日
 */
public class ClassLoaderCheck {
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("E:\\project3\\","myClassLoader");
        try {
            Class c = myClassLoader.loadClass("Code");
            System.out.println(c.getClassLoader());
            c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
