package com.august.sync;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月20日
 */
public class Sync {
    public  void t1() throws InterruptedException {
        synchronized(Sync.class){
            System.out.println("t1");
            System.out.println(Thread.currentThread().getName());
            this.t2();
        }
    }
    public synchronized void t2() throws InterruptedException {
        synchronized(Sync.class) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000);

        }
    }
}
