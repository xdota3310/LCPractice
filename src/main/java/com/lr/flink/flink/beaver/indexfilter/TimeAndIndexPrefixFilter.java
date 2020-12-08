package com.lr.flink.flink.beaver.indexfilter;

import cn.yottabyte.search.IndexName;

/**
 * @author chengtao
 * @date 26/11/2020 - 15:09
 */
public class TimeAndIndexPrefixFilter implements IndexFilter {

    private long startTime;
    private long endTime;
    private String indexPrefix;

    public TimeAndIndexPrefixFilter(String indexPrefix, long startTime, long endTime) {
        this.indexPrefix = indexPrefix;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean filterIndex(IndexName indexName) {
        return indexName != null && indexName.getCompletedName()!= null
                && indexName.getCompletedName().startsWith(indexPrefix)
                && checkIndexInTimeRange(indexName);
    }

    private boolean checkIndexInTimeRange(IndexName indexName) {
        return !(indexName.getEndTime() <= startTime || indexName.getStartTime() >= endTime);
    }
}
