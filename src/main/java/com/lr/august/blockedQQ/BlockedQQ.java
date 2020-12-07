package com.august.blockedQQ;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月21日
 */
public class BlockedQQ<T> {
    private final Queue<T> queue = new ArrayDeque<>();


    public void add(T t) {
        synchronized(this) {
            System.out.println(Thread.currentThread().getName() + " {add}");
            while(queue.size() >= 16) {
                System.out.println(Thread.currentThread().getName() + " add waiting!");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(t);
            notifyAll();
        }
    }

    public T get() {
        synchronized(this) {
            System.out.println(Thread.currentThread().getName() + " {get}");
            while(queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " get waiting!");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T res = queue.poll();
            notifyAll();
            return res;
        }
    }


}
