package com.august.BlockedQQQ;

import java.util.concurrent.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月19日
 */
public class BlockedQTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BlockedQ<Integer> blockedQ = BlockedQ.getIns();
        System.out.println(blockedQ);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> t1 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for(int i=0;i<20;i++){
                    blockedQ.doPush(i);
                }
                return null;
            }
        });
        t1.get();
    }
}
