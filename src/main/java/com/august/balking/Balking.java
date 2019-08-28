package com.august.balking;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
            System.out.println("autodo");
            change = false;
        }
        this.todo();
    }

    void todo() {
        System.out.println("todo");
    }

    void edit() {
        //dosomething
        System.out.println("doSomething");
        this.change();
    }

    private void change() {
        synchronized(this) {
            change = true;
        }
        System.out.println("change");
    }

    public static void main(String[] args) {
        Balking balking = new Balking();
        ThreadGroup threadGroup = new ThreadGroup("balkingThreadGroupTest!");
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(3, r -> new Thread(threadGroup, r, "balkingTest!"));
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println("time to auto：" + new Date());
            balking.autodo();
        }, 1, 3, TimeUnit.SECONDS);

        executorService.schedule(balking::edit, 10, TimeUnit.SECONDS);
    }


}
