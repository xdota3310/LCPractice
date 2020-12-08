package com.lr.flink.flink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengtao
 * @date 24/11/2020 - 21:10
 */
public class TrainLocalTest {

    private static Logger logger = LoggerFactory.getLogger(TrainLocalTest.class);


    static void remoteTest() throws Exception {
        FlinkConfig flinkConfig = FlinkConfig.buildFrom("/Users/chengtao/yottabyte/yotta_backends/loganalyzer/config/timtest.yml");
        TrainJobBundle trainJobBundle = new TrainJobBundle();
        trainJobBundle.setEarliest(0);
        trainJobBundle.setLatest(Long.MAX_VALUE);
        trainJobBundle.setIndexPrefix("ops");
        trainJobBundle.setName("tim-job");
        trainJobBundle.setPunctuationMask(true);
        trainJobBundle.setStopWordMask(false);
        trainJobBundle.setQueryString("*");
        trainJobBundle.setTrainJobParallelism(4);
        trainJobBundle.setMaxRowCountPerPartition(100000);
        TrainJobExecutor executor = new FlinkTrainJobExecutor(flinkConfig);
        logger.info(executor.submit(trainJobBundle).get().toString());

    }

    static void localTest(String[] args) throws Exception {

        FlinkJobArgs flinkJobArgs = FlinkJobArgsParser.parse(args);
        FlinkTrainJob flinkTrainJob = new FlinkTrainJob(flinkJobArgs);
        flinkTrainJob.run();

    }

    public static void main(String[] args) throws Exception {
        localTest(args);
    }
}
