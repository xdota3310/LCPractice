package com.august.BlockedQQQ;

import java.util.concurrent.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月19日
 */
public class BlockedQTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BlockedQ<Integer> blockedQ = BlockedQ.getIns();
        System.out.println(blockedQ);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> t1 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                while(true){
                    blockedQ.doPoll();
                    Thread.sleep(1000);
                }
            }
        });
        Future<Integer> t2 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                while(true){
                    blockedQ.doPoll();
                    Thread.sleep(2000);
                }
            }
        });
        Future<Integer> t3 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                while(true){
                    blockedQ.doPoll();
                    Thread.sleep(3000);
                }
            }
        });
        Thread.sleep(10000);
        Future<Integer> t11 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for(int i=0;i<20;i++){
                    blockedQ.doPush(i);
                    Thread.sleep(100);
                }
                return null;
            }
        });
        Future<Integer> t22 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for(int i=0;i<20;i++){
                    blockedQ.doPush(i);
                    Thread.sleep(200);
                }
                return null;
            }
        });
//        Future<Integer> t4 = executorService.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                blockedQ.doPoll(11000);
//                return null;
//            }
//        });
//        Future<Integer> t5 = executorService.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                blockedQ.doPoll(12000);
//                return null;
//            }
//        });
    }
}
