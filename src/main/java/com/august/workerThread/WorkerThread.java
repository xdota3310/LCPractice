package com.august.workerThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;

/**
 * Worker Thread模式可以类比现实世界里车间的工作模式：车间里的工人，有活儿了，大家一起干，没活儿
 * 了就聊聊天等着。你可以参考下面的示意图来理解，Worker Thread模式中 Worker Thread对应到现实世界
 * 里，其实指的就是车间里的工人。不过这里需要注意的是，车间里的工人数量往往是确定的。
 *
 * 简易线程池实现
 *
 * @author shijie.xu
 * @since 2019年08月28日
 */
public class WorkerThread {
    private BlockingQueue<Runnable> queue;
    private int size;
    private ThreadFactory threadFactory;

    /**
     * 设置自己的线程终止标志位
     *
     * 原因在于我们很可能在线程的run()方法中调用第三方类库提供的方法，
     * 而我们没有办法保证第三方类库正确处理了线程的中断异常，
     * 例如第三方类库在捕获到Thread.sleep()方法抛出的中断异常后，
     * 没有重新设置线程的中断状态，那么就会导致线程不能够正常终止
     */
    private boolean interrupted = false;

    /**
     * true: queue 不再能入队新任务
     * 默认为: false
     */
    private boolean queueBlock = false;

    private List<Thread> workThreads = new ArrayList<>();

    public WorkerThread(BlockingQueue<Runnable> queue, int size, ThreadFactory threadFactory) {
        this.queue = queue;
        this.size = size;
        this.threadFactory = threadFactory;
        run();
    }

    void execute(Runnable runnable) {
        if(!queueBlock) {
            try {
                queue.put(runnable);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        } else {
            throw new PoolHasBeenInterruptedException("pool has been interrupted!");
        }

    }

    void shutDown() {
        queueBlock = true;
        while(!queue.isEmpty()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        interrupted = true;
        for(int i = 0; i < size; i++) {
            workThreads.get(i).interrupt();
        }
    }

    private void run() {
        for(int i = 0; i < size; i++) {
            WorkThread thread = new WorkThread();
            Thread t = threadFactory.newThread(thread);
            workThreads.add(t);
            System.out.println(t.getName() + ": init");
            t.start();
        }
    }

    class WorkThread implements Runnable {
        @Override
        public void run() {
            while(!interrupted) {
                try {
                    Runnable r = queue.take();
                    r.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }

    class PoolHasBeenInterruptedException extends RuntimeException {
        PoolHasBeenInterruptedException(String message) {
            super(message);
        }
    }

}
