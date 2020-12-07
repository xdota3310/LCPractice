package com.august.countCyclic;

import java.util.concurrent.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月21日
 */
public class CountT {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cl = new CountDownLatch(2);
        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<String> f = es.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("first");
                Thread.sleep(1000);
                cl.countDown();
                System.out.println("first2");
                return "first";
            }
        });

        Future<String> f2 = es.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("sec");
                Thread.sleep(10000);
                cl.countDown();
                System.out.println("sec2");
                return "sec";
            }
        });

        cl.await();
        System.out.println("main");
        Thread.sleep(3000);
        System.out.println("main2");
    }
}
