package com.lr.flink.flink;

import cn.yottabyte.flink.beaver.BeaverConfig;
import cn.yottabyte.flink.mongo.MongoConfig;
import org.elasticsearch.common.settings.Settings;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author chengtao
 * @date 19/11/2020 - 16:36
 */
public class FlinkJobArgs implements Serializable {

    public static final long serialVersionUID = 1L;

    public static final String SETTING_CONFIG_PATH = "config_path";
    public static final String TRAIN_BUNDLE_PATH = "train_bundle_path";
    public static final String JOB_PARALLELISM = "flink.job.parallelism";

    private Settings settings;
    private MongoConfig mongoConfig;
    private BeaverConfig beaverConfig;
    private TrainJobBundle trainJobBundle;

    public Settings getSettings() {
        return settings;
    }

    public FlinkJobArgs setSettings(Settings settings) {
        this.settings = settings;
        return this;
    }

    public MongoConfig getMongoConfig() {
        return mongoConfig;
    }

    public FlinkJobArgs setMongoConfig(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
        return this;
    }

    public BeaverConfig getBeaverConfig() {
        return beaverConfig;
    }

    public FlinkJobArgs setBeaverConfig(BeaverConfig beaverConfig) {
        this.beaverConfig = beaverConfig;
        return this;
    }

    public TrainJobBundle getTrainJobBundle() {
        return trainJobBundle;
    }

    public FlinkJobArgs setTrainJobBundle(TrainJobBundle trainJobBundle) {
        this.trainJobBundle = trainJobBundle;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FlinkJobArgs.class.getSimpleName() + "[", "]")
                .add("mongoConfig=" + mongoConfig)
                .add("beaverConfig=" + beaverConfig)
                .add("trainJobBundle=" + trainJobBundle)
                .toString();
    }
}
