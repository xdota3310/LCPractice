package com.august.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * 线程池
 *
 * @author shijie.xu
 * @since 2019年08月22日
 */
public class ThreadPP {
    private BlockingQueue<Runnable> queue;

    List<ThisThread> threads = new ArrayList<>();

    public ThreadPP(BlockingQueue<Runnable> queue, int size) {
        this.queue = queue;
        for(int i = 0; i < size; i++) {
            ThisThread t= new ThisThread();
            t.start();
            threads.add(t);
        }
    }

    public void execute(Runnable com) throws InterruptedException {
        queue.put(com);
    }

    class ThisThread extends Thread {

        @Override
        public void run() {
            while(true) {
                try {
                    Runnable task = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
