package com.august.sync;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月14日
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest = new SyncTest();
        int i = 1;
//        Thread thread = new Thread(syncTest, String.valueOf(i++));
//        Thread thread2 = new Thread(syncTest, String.valueOf(i++));
//        Thread thread3 = new Thread(syncTest, String.valueOf(i++));
//        Thread thread4 = new Thread(syncTest, String.valueOf(i++));
//        Thread thread5 = new Thread(syncTest, String.valueOf(i++));
//        Thread thread6 = new Thread(syncTest, String.valueOf(i++));
//        Thread thread7 = new Thread(syncTest, String.valueOf(i++));
//        Thread thread8 = new Thread(syncTest, String.valueOf(i++));
//        Thread thread9 = new Thread(syncTest, String.valueOf(i++));
//        Thread thread10 = new Thread(syncTest, String.valueOf(i++));
//        thread.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
//        thread6.start();
//        thread7.start();
//        thread8.start();
//        thread9.start();
//        thread10.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Sync().t1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Sync().t2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
