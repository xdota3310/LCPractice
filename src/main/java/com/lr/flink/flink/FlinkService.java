package com.lr.flink.flink;

import cn.yottabyte.util.LoggerFactory;
import com.google.common.base.Preconditions;
import org.apache.flink.api.common.JobSubmissionResult;
import org.apache.flink.client.program.PackagedProgram;
import org.apache.flink.client.program.PackagedProgramUtils;
import org.apache.flink.client.program.rest.RestClusterClient;
import org.apache.flink.configuration.AkkaOptions;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.JobManagerOptions;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.runtime.jobgraph.JobGraph;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

/**
 * @author chengtao
 * @date 23/11/2020 - 21:24
 */
public class FlinkService {

    private static final Logger logger = LoggerFactory.getLogger(FlinkService.class);
    public static final String FLINK_CLUSTER_ID = "RemoteStreamEnvironment";

    private Configuration configuration;
    private RestClusterClient client;
    private int defaultParallelism;

    public FlinkService(FlinkConfig flinkConfig) throws Exception {
        Preconditions.checkNotNull(flinkConfig.getTrainJobBundlePath());
        Preconditions.checkNotNull(flinkConfig.getConfigPath());
        Preconditions.checkNotNull(flinkConfig.getFlinkJobParallelism());
        Preconditions.checkNotNull(flinkConfig.getJarPath());
        Preconditions.checkNotNull(flinkConfig.getFlinkMaster());

        URL url = new URL("http://" + flinkConfig.getFlinkMaster());
        this.configuration = new Configuration();
        configuration.setString(JobManagerOptions.ADDRESS, url.getHost());
        configuration.setInteger(JobManagerOptions.PORT, url.getPort());
        configuration.setInteger(RestOptions.PORT, url.getPort());
        configuration.setString(AkkaOptions.CLIENT_TIMEOUT, "30 s");

        this.client = new RestClusterClient(configuration, FLINK_CLUSTER_ID);
        this.defaultParallelism = flinkConfig.getFlinkJobParallelism();
        logger.error("flink config: {} {}", flinkConfig, configuration.toMap());
    }

    public CompletableFuture<JobSubmissionResult> submitJob(PackagedProgram program) {
        try {
            logger.info("submit job " + program.getDescription());
            JobGraph jobGraph = PackagedProgramUtils.createJobGraph(program,
                    configuration, defaultParallelism);
            return client.submitJob(jobGraph);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
