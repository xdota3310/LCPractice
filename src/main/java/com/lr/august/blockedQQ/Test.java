package com.august.blockedQQ;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月21日
 */
public class Test {
    public static void main(String[] args) {
        BlockedQQ blockedQQ = new BlockedQQ();
        new Thread(new Runnable() {
            @Override
            public void run() {
                blockedQQ.get();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockedQQ.get();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                blockedQQ.get();
            }
        }).start();
    }
}
