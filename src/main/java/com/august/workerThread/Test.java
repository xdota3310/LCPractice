package com.august.workerThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月29日
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger i = new AtomicInteger(0);
        WorkerThread workerThread  =
        new WorkerThread(new ArrayBlockingQueue<>(10),3,r -> new Thread(r,"SimplePool-"+ i.incrementAndGet()));

        workerThread.execute(() -> {
            System.out.println(Thread.currentThread().getName()+": start");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": end");
        });
        workerThread.execute(() -> {
            System.out.println(Thread.currentThread().getName()+": start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": end");
        });
        workerThread.execute(() -> {
            System.out.println(Thread.currentThread().getName()+": start");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": end");
        });
        workerThread.execute(() -> {
            System.out.println(Thread.currentThread().getName()+": start11");
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": end11");
        });

    }

}
