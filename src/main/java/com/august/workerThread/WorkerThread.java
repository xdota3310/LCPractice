package com.august.workerThread;

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

    public WorkerThread(BlockingQueue<Runnable> queue, int size, ThreadFactory threadFactory) {
        this.queue = queue;
        this.size = size;
        this.threadFactory = threadFactory;
        run();
    }

    void execute(Runnable runnable) throws InterruptedException {
        queue.put(runnable);
    }

    private void run() {
        for(int i = 0; i < size; i++) {
            try {
                Thread t = threadFactory.newThread(queue.take());
                t.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
