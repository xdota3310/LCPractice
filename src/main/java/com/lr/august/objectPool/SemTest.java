package com.august.objectPool;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月20日
 */
public class SemTest {
    private int base;

    public SemTest(int base) {
        this.base = base;
    }

    public void add(int n) {
        System.out.println(n + base);
    }


    public int getBase() {
        return base;
    }
}
