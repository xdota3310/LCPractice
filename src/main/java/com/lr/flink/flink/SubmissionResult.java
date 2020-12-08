package com.lr.flink.flink;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author chengtao
 * @date 25/11/2020 - 14:43
 */
public class SubmissionResult {
    private byte[] jobId;

    public byte[] getJobId() {
        return jobId;
    }

    public SubmissionResult setJobId(byte[] jobId) {
        this.jobId = jobId;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SubmissionResult.class.getSimpleName() + "[", "]")
                .add("jobId=" + Arrays.toString(jobId))
                .toString();
    }
}
