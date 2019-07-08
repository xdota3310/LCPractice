package com.July.P1;

import java.util.ArrayList;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年07月01日
 */


/**
 * Created by ibm on 2017/8/8.
 */
public class P1 {

    //使用原始的synchornized object.wait() object.notify()
    public static void main(String[] args) {
        //定义线程组
        List<MyThread> threadGroups = new ArrayList<>();
        Counter counter = new Counter();
        MyThread t1 = new MyThread(1, "一", counter, threadGroups);
        MyThread t2 = new MyThread(2, "二", counter, threadGroups);
        MyThread t3 = new MyThread(2, "三", counter, threadGroups);
        threadGroups.add(t1);
        threadGroups.add(t2);
        threadGroups.add(t3);
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }
}

class MyThread implements Runnable {
    //线程运行状态 1马上执行，2阻塞
    public int status;
    //线程名字
    public String name;
    //计数器
    public Counter counter;
    //线程组
    public List<MyThread> threads = new ArrayList<>();

    public MyThread(int status, String name, Counter counter, List<MyThread> threads) {
        this.status = status;
        this.name = name;
        this.counter = counter;
        this.threads = threads;
    }

    @Override
    public void run() {
        System.out.println(name + " GET " + status);
        synchronized(counter) {
            while(!counter.isEnd()) {
                //判断是否该自己执行，切记使用while,因为如果在循环的等待过程中status有所变化，这里需要再次判断
                while(status != 1) {
                    try {
                        counter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int i = 0; i < 5; i++) {
                    System.out.println(name + ":" + counter.get());
                    counter.increase();
                }
                //状态进入阻塞状态，并设置下一个可以运行的线程
                status = 2;
                setNext();
                counter.notifyAll();
                System.out.println("----");
            }
        }
    }

    void setNext() {
        //当前线程在线程组的索引
        int index = 0;
        for(index = 0; index < threads.size(); index++) {
            if(name.equals(threads.get(index).name)) {
                break;
            }
        }
        if(index == (threads.size() - 1)) {
            threads.get(0).status = 1;
        } else {
            threads.get(index + 1).status = 1;
        }
    }
}

class Counter {

    int num = 1;
    int end = 75;

    public int get() {
        return num;
    }

    public void increase() {
        if(isEnd()) {
            System.out.println("num超过限制");
            return;
        }
        num++;
    }

    public boolean isEnd() {
        if(num >= end) {
            return true;
        }
        return false;
    }
}

