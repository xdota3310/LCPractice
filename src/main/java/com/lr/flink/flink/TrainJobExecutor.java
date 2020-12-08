package com.lr.flink.flink;

import java.util.concurrent.CompletableFuture;

/**
 * @author chengtao
 * @date 24/11/2020 - 15:33
 */
interface TrainJobExecutor {

    CompletableFuture<SubmissionResult> submit(TrainJobBundle bundle);

}
