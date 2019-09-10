package com.august.balking;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Balking模式本质上是一种规范化地解决“多线程版本的if”的方案，使用
 * Balking模式规范化之后的写法如下所示，你会发现仅仅是将edit()方法中对共享变量changed的赋值操作抽
 * 取到了change()中，这样的好处是将并发处理逻辑和业务逻辑分开。
 *
 * @author shijie.xu
 * @since 2019年08月27日
 */
public class Balking {
    private boolean change = false;

    /**
     * 通过定时调用该方法的方式
     * 自动实现某个功能
     */
    void autodo() {
        synchronized(this) {
            if(!change) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + ": autodo");
            change = false;
        }
        this.todo();
    }

    void todo() {
        System.out.println(Thread.currentThread().getName() + ": todo");
    }

    void edit() {
        //dosomething
        System.out.println(Thread.currentThread().getName() + ": doSomething");
        this.change();
    }

    private void change() {
        synchronized(this) {
            change = true;
        }
        System.out.println(Thread.currentThread().getName() + ": change");
    }

    public static void main(String[] args) {
        Balking balking = new Balking();
        ThreadGroup threadGroup = new ThreadGroup("balkingThreadGroupTest!");
        AtomicInteger i = new AtomicInteger();
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(3, r -> {
            String name = "balkingTest!";
            return new Thread(threadGroup, r, name + "-" + i.incrementAndGet());
        });
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + ": time to auto：" + new Date());
            balking.autodo();
        }, 1, 3, TimeUnit.SECONDS);

        executorService.schedule(balking::edit, 10, TimeUnit.SECONDS);
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
        System.out.println(Thread.currentThread().getName() + ": 1231");
    }
}
