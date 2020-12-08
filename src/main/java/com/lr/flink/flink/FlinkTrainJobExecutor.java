package com.lr.flink.flink;

import org.apache.flink.api.common.JobSubmissionResult;
import org.apache.flink.client.program.PackagedProgram;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author chengtao
 * @date 23/11/2020 - 20:54
 */
public class FlinkTrainJobExecutor implements TrainJobExecutor {

    private FlinkConfig flinkConfig;
    private final FlinkService flinkService;
    private final TrainJobBundleStore trainJobBundleStore;

    public FlinkTrainJobExecutor(FlinkConfig flinkConfig) throws Exception {
        this.flinkConfig = flinkConfig;
        this.flinkService = new FlinkService(flinkConfig);
        this.trainJobBundleStore = new TrainJobBundleStore(flinkConfig.getTrainJobBundlePath());
    }

    @Override
    public CompletableFuture<SubmissionResult> submit(TrainJobBundle trainJobBundle) {

        try {
            String bundlePath = trainJobBundleStore.store(trainJobBundle);
            List<String> args = new ArrayList<>(8);
            args.add("--" + FlinkJobArgs.SETTING_CONFIG_PATH);
            args.add(flinkConfig.getConfigPath());
            args.add("--" + FlinkJobArgs.TRAIN_BUNDLE_PATH);
            args.add(bundlePath);

            PackagedProgram program = new PackagedProgram(new File(flinkConfig.getJarPath()),
                    TrainOnFlinkMain.class.getName(), args.toArray(new String[args.size()]));

            CompletableFuture<JobSubmissionResult> future = flinkService.submitJob(program);
            return future.thenApply(new Function<JobSubmissionResult, SubmissionResult>() {
                @Override
                public SubmissionResult apply(JobSubmissionResult flinkSubRes) {
                    SubmissionResult submissionResult = new SubmissionResult();
                    submissionResult.setJobId(flinkSubRes.getJobID().getBytes());
                    return submissionResult;
                }
            });

        } catch (Exception ex) {
            throw new RuntimeException("execute train program error", ex);
        }
    }
}
