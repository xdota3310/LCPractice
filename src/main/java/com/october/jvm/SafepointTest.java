package com.october.jvm;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年11月01日
 */
public class SafepointTest {
    static double sum = 0;

    public static void foo() {
        for(int i = 0; i < 0x7777_7777; i++) {
            sum += Math.sqrt(i);
        }
    }

    public static void bar() {
        for(int i = 0; i < 50_000_000; i++) {
            new Object().hashCode();
        }
    }

    public static void main(String[] args) {
        new Thread(SafepointTest::foo).start();
        new Thread(SafepointTest::bar).start();
    }

}
