package com.august.BlockedQQQ;

import java.util.concurrent.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月19日
 */
public class BlockedQTest {
    public static void main(String[] args) {
        BlockedQ<Integer> blockedQ = BlockedQ.getIns();
        System.out.println(blockedQ);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> t1 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                blockedQ.doPoll();
                return null;
            }
        });
        Future<Integer> t2 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                blockedQ.doPoll();
                return null;
            }
        });
        Future<Integer> t3 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                blockedQ.doPoll();
                return null;
            }
        });
        Future<Integer> t4 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                blockedQ.doPoll();
                return null;
            }
        });
        Future<Integer> t5 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                blockedQ.doPoll();
                return null;
            }
        });
    }
}
