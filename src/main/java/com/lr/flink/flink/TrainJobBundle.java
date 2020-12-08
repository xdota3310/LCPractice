package com.lr.flink.flink;

import cn.yottabyte.rest.RegexConfig;

import java.io.Serializable;
import java.util.List;

/**
 * @author chengtao
 * @date 23/11/2020 - 20:07
 */
public class TrainJobBundle implements Serializable {

    private static long serialVersionUID = -1;

    /**
     * query time range
     */
    private String indexPrefix;
    private String queryString;
    private long earliest;
    private long latest;

    /**
     * for train job
     */
    private String name;
    private boolean stopWordMask;
    private boolean punctuationMask;
    private List<RegexConfig> regexConfigList;

    /**
     * for flink
     */
    private Integer trainJobParallelism;
    private Integer maxRowCountPerPartition;

    public long getEarliest() {
        return earliest;
    }

    public TrainJobBundle setEarliest(long earliest) {
        this.earliest = earliest;
        return this;
    }

    public long getLatest() {
        return latest;
    }

    public TrainJobBundle setLatest(long latest) {
        this.latest = latest;
        return this;
    }

    public String getName() {
        return name;
    }

    public TrainJobBundle setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isStopWordMask() {
        return stopWordMask;
    }

    public TrainJobBundle setStopWordMask(boolean stopWordMask) {
        this.stopWordMask = stopWordMask;
        return this;
    }

    public boolean isPunctuationMask() {
        return punctuationMask;
    }

    public TrainJobBundle setPunctuationMask(boolean punctuationMask) {
        this.punctuationMask = punctuationMask;
        return this;
    }

    public List<RegexConfig> getRegexConfigList() {
        return regexConfigList;
    }

    public TrainJobBundle setRegexConfigList(List<RegexConfig> regexConfigList) {
        this.regexConfigList = regexConfigList;
        return this;
    }

    public String getIndexPrefix() {
        return indexPrefix;
    }

    public TrainJobBundle setIndexPrefix(String indexPrefix) {
        this.indexPrefix = indexPrefix;
        return this;
    }

    public String getQueryString() {
        return queryString;
    }

    public TrainJobBundle setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public Integer getTrainJobParallelism() {
        return trainJobParallelism;
    }

    public TrainJobBundle setTrainJobParallelism(Integer trainJobParallelism) {
        this.trainJobParallelism = trainJobParallelism;
        return this;
    }

    public Integer getMaxRowCountPerPartition() {
        return maxRowCountPerPartition;
    }

    public TrainJobBundle setMaxRowCountPerPartition(Integer maxRowCountPerPartition) {
        this.maxRowCountPerPartition = maxRowCountPerPartition;
        return this;
    }
}
