package com.lr.flink.flink;

/**
 * @author chengtao
 * @date 17/11/2020 - 15:59
 */
public class TrainOnFlinkMain {

    public static void main(String[] args) throws Exception {

        FlinkJobArgs flinkJobArgs = FlinkJobArgsParser.parse(args);
        FlinkTrainJob flinkTrainJob = new FlinkTrainJob(flinkJobArgs);
        flinkTrainJob.run();

    }
}
