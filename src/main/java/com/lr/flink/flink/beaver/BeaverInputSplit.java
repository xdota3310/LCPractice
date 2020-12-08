package com.lr.flink.flink.beaver;

import org.apache.flink.core.io.InputSplit;

/**
 * @author chengtao
 * @date 26/11/2020 - 10:49
 */
public class BeaverInputSplit implements InputSplit {
    private final int splitNumber;
    private final BeaverShard beaverShard;

    public BeaverInputSplit(int splitNumber, BeaverShard beaverShard) {
        this.splitNumber = splitNumber;
        this.beaverShard = beaverShard;
    }

    @Override
    public int getSplitNumber() {
        return splitNumber;
    }

    public BeaverShard getBeaverShard() {
        return beaverShard;
    }
}
