package com.lr.flink.flink.beaver;

import org.apache.flink.core.io.InputSplit;
import org.apache.flink.core.io.InputSplitAssigner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ShuffleInputSplitAssigner implements InputSplitAssigner  {
    private static final Logger LOG = LoggerFactory.getLogger(ShuffleInputSplitAssigner.class);
    private final List<BeaverInputSplit> splits;
    public ShuffleInputSplitAssigner(BeaverInputSplit[] splits) {
        this.splits = shuffleSplits(splits);
    }

    private List<BeaverInputSplit> shuffleSplits(BeaverInputSplit[] splits) {
        Arrays.sort(splits, new Comparator<BeaverInputSplit>() {
            @Override
            public int compare(BeaverInputSplit o1, BeaverInputSplit o2) {
                String index1 = o1.getBeaverShard().getShardKey().getIndex();
                String index2 = o2.getBeaverShard().getShardKey().getIndex();
                return index1.compareTo(index2);
            }
        });
        Map<Integer, List<BeaverInputSplit>> splitMap = new HashMap<>();
        List<BeaverInputSplit> nullPartitionList = new ArrayList<>();
        for (BeaverInputSplit split : splits) {
            if (split.getBeaverShard() != null) {
                int ip = split.getBeaverShard().getShardCopies().get(0).getNetAddressIp();
                List<BeaverInputSplit> splitList = splitMap.get(ip);
                if (splitList == null) {
                    splitList = new ArrayList<>();
                    splitMap.put(ip, splitList);
                }
                splitList.add(split);
            } else {
                nullPartitionList.add(split);
            }
        }

        List<BeaverInputSplit> shuffledList = new ArrayList<>(splits.length);
        while (!splitMap.isEmpty()) {
            Iterator<Map.Entry<Integer, List<BeaverInputSplit>>> iter = splitMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<Integer, List<BeaverInputSplit>> entry = iter.next();
                BeaverInputSplit split = entry.getValue().remove(0);
                shuffledList.add(split);
                if (entry.getValue().isEmpty()) {
                    iter.remove();
                }
            }
        }
        shuffledList.addAll(nullPartitionList);
        return shuffledList;

    }

    @Override
    public InputSplit getNextInputSplit(String host, int taskId) {
        InputSplit next = null;
        synchronized(this.splits) {
            if (this.splits.size() > 0) {
                next = (InputSplit)this.splits.remove(this.splits.size() - 1);
            }
        }

        if (LOG.isDebugEnabled()) {
            if (next == null) {
                LOG.debug("No more input splits available");
            } else {
                LOG.debug("Assigning split " + next + " to " + host);
            }
        }

        return next;
    }

    @Override
    public void returnInputSplit(List<InputSplit> splits, int taskId) {
        synchronized(this.splits) {
            Iterator iter = splits.iterator();
            while(iter.hasNext()) {
                BeaverInputSplit split = (BeaverInputSplit)iter.next();
                this.splits.add(split);
            }

        }
    }

}
