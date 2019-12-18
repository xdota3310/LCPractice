package com.august.fork_join;

import java.util.concurrent.*;

/**
 * 使用Fork/join框架  计算1+2+3+4
 *
 * @author shijie.xu
 * @since 2019年12月17日
 */
public class CountTask extends RecursiveTask<Integer> {
    /**
     * 阀值
     */
    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute) {
            for(int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int mid = (start + end) >>> 2;
            CountTask rTask = new CountTask(start, mid);
            CountTask lTask = new CountTask(mid + 1, end);
            lTask.fork();
            rTask.fork();

            int lRes = lTask.join();
            int rRes = rTask.join();

            sum = lRes + rRes;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future future = forkJoinPool.submit(new CountTask(1, 4));
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
