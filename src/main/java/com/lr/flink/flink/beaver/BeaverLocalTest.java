package com.lr.flink.flink.beaver;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author chengtao
 * @date 26/11/2020 - 16:56
 */
public class BeaverLocalTest {

    public static void main(String[] args) throws Exception {
        BeaverConfig beaverConfig = new BeaverConfig();
        beaverConfig.setZkPath("/beaver/rizhiyi/brokers");
        beaverConfig.setNodes(Lists.newArrayList("192.168.1.143:50060:50061"));
        SearchBundle searchBundle = new SearchBundle();
        searchBundle.setQueryString("*")
                .setIndexPrefix("ops")
                .setFields(Sets.newHashSet("timestamp"))
                // .setEarliest(1501776000000L)
                .setEarliest(0L)
                .setLatest(1501862400000L);

        BeaverInputFormat beaverInputFormat = new BeaverInputFormat(beaverConfig, searchBundle);
        BeaverInputSplit[] splits = beaverInputFormat.createInputSplits(1);
        for (BeaverInputSplit split : splits) {
            beaverInputFormat.open(split);
            if (!beaverInputFormat.reachedEnd()) {
                Row row = beaverInputFormat.nextRecord(null);
                System.out.println(row);
            }
        }
    }

}
