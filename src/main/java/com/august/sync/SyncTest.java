package com.august.sync;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月14日
 */
public class SyncTest implements Runnable {
    private volatile int flag = 3;

    @Override
    public void run() {
        getFlag();
    }

    private void getFlag() {
        if(flag > 0) {
            System.out.println(Thread.currentThread().getName() + ":" + --flag);
        }
    }


}
