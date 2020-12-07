package com.august.dothread;

import java.util.concurrent.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月13日
 */
public class DoThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("当前线程："+Thread.currentThread().getName());
        ThreadTest threadTest = new ThreadTest(1232132132);
        Thread t = new Thread(threadTest);
        t.start();
        t.join();
        System.out.println("------------------------------------");

        FutureTask<String> ft = new FutureTask<>(new CallableTest());
        new Thread(ft).start();
        System.out.println("return: "+ft.get());
        System.out.println("------------------------------------");

        ExecutorService es = Executors.newCachedThreadPool();
        Future<String> f = es.submit(new CallableTest());
        System.out.println("return: "+f.get());
        es.shutdown();
        System.out.println("------------------------------------");


        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a waiting to get lock");
                synchronized(lock){

                }
            }
        }).start();

    }
}
