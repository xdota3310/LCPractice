package com.august.dothread;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月13日
 */
public class ThreadTest implements Runnable {
    private int i;

    public ThreadTest(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("i: "+ i);
        System.out.println(this.getClass().getName()+": "+Thread.currentThread().getName());
    }
}
