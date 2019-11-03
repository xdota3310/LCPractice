package com.october;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年10月23日
 */
public class LockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        AtomicInteger flag = new AtomicInteger(1);
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        executorService.execute(() -> {
            lock.lock();
            try {
                while(true){
                    while(flag.get() != 1) {
                        condition1.await();
                    }
                    System.out.println("1");
                    flag.set(2);
                    condition2.signalAll();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        });

        executorService.execute(() -> {
            lock.lock();
            try {
                while(true){
                    while(flag.get() != 2) {
                        condition2.await();
                    }
                    System.out.println("2");
                    flag.set(3);
                    condition3.signalAll();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        });

        executorService.execute(() -> {
            lock.lock();
            try {
                while(true){
                    while(flag.get() != 3) {
                        condition3.await();
                    }
                    System.out.println("3");
                    flag.set(1);
                    condition1.signalAll();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        });
    }
}
