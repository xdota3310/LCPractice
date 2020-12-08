package com.lr.flink.flink;

import org.apache.flink.util.Preconditions;
import org.elasticsearch.common.settings.Settings;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

/**
 * @author chengtao
 * @date 24/11/2020 - 11:49
 */
public class FlinkConfig {

    public static final String FLINK_MASTER = "flink.master";
    public static final String FLINK_JOB_PARALLELISM = "flink.job.parallelism";
    public static final String JAR_PATH = "flink.jar.path";
    public static final String TRAIN_JOB_BUNDLE_PATH = "flink.job.bundle_path";

    private String flinkMaster;
    private Integer flinkJobParallelism;
    private String jarPath;
    private String configPath;
    private String trainJobBundlePath;

    private FlinkConfig() {}

    public static FlinkConfig buildFrom(String configPath) {

        Settings settings;
        try {
            settings = Settings.builder().loadFromPath(new File(configPath).toPath()).build();
        } catch (IOException ex) {
            throw new RuntimeException("error loading " + configPath, ex);
        }

        FlinkConfig flinkConfig = new FlinkConfig();
        flinkConfig.setFlinkMaster(settings.get(FLINK_MASTER));
        Preconditions.checkNotNull(flinkConfig.getFlinkMaster());

        flinkConfig.setFlinkJobParallelism(Integer.valueOf(settings.get(FLINK_JOB_PARALLELISM)));
        flinkConfig.setJarPath(settings.get(JAR_PATH));

        Preconditions.checkNotNull(flinkConfig.getJarPath());
        Preconditions.checkNotNull(flinkConfig.getFlinkJobParallelism());

        flinkConfig.setConfigPath(configPath);
        flinkConfig.setTrainJobBundlePath(settings.get(TRAIN_JOB_BUNDLE_PATH));

        Preconditions.checkNotNull(flinkConfig.getConfigPath());
        Preconditions.checkNotNull(flinkConfig.getTrainJobBundlePath());

        return flinkConfig;
    }

    public String getFlinkMaster() {
        return flinkMaster;
    }

    public FlinkConfig setFlinkMaster(String flinkMaster) {
        this.flinkMaster = flinkMaster;
        return this;
    }

    public String getJarPath() {
        return jarPath;
    }

    public FlinkConfig setJarPath(String jarPath) {
        this.jarPath = jarPath;
        return this;
    }

    public String getConfigPath() {
        return configPath;
    }

    public FlinkConfig setConfigPath(String configPath) {
        this.configPath = configPath;
        return this;
    }

    public FlinkConfig setFlinkJobParallelism(Integer flinkJobParallelism) {
        this.flinkJobParallelism = flinkJobParallelism;
        return this;
    }

    public String getTrainJobBundlePath() {
        return trainJobBundlePath;
    }

    public FlinkConfig setTrainJobBundlePath(String trainJobBundlePath) {
        this.trainJobBundlePath = trainJobBundlePath;
        return this;
    }

    public Integer getFlinkJobParallelism() {
        return flinkJobParallelism;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FlinkConfig.class.getSimpleName() + "[", "]")
                .add("flinkMaster='" + flinkMaster + "'")
                .add("flinkJobParallelism=" + flinkJobParallelism)
                .add("jarPath='" + jarPath + "'")
                .add("configPath='" + configPath + "'")
                .add("trainJobBundlePath='" + trainJobBundlePath + "'")
                .toString();
    }
}
