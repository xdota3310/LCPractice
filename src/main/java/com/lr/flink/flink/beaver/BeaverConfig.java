package com.lr.flink.flink.beaver;


import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.elasticsearch.common.settings.Settings;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author chengtao
 * @date 20/11/2020 - 20:07
 */
public class BeaverConfig implements Serializable {
    public static final long serialVersionUID = 1L;

    public static final String FN_NODES = "data_source.beaver.nodes";
    public static final String FN_ZK_PATH = "data_source.beaver.zk_path";

    public static final String NODES = "nodes";
    public static final String ZK_PATH = "zkPath";

    private List<String> nodes;
    private String zkPath;

    public List<String> getNodes() {
        return nodes;
    }

    public BeaverConfig setNodes(List<String> nodes) {
        this.nodes = nodes;
        return this;
    }

    public String getZkPath() {
        return zkPath;
    }

    public BeaverConfig setZkPath(String zkPath) {
        this.zkPath = zkPath;
        return this;
    }

    public static BeaverConfig buildFromSettings(Settings settings) {
        BeaverConfig beaverConfig = new BeaverConfig();
        beaverConfig.setNodes(Arrays.asList(settings.getAsArray(FN_NODES)));
        beaverConfig.setZkPath(settings.get(FN_ZK_PATH));

        Preconditions.checkNotNull(beaverConfig.getNodes());
        Preconditions.checkNotNull(beaverConfig.getZkPath());

        return beaverConfig;
    }

    public Config toTypeSafeConfig() {
        return ConfigFactory.parseMap(ImmutableMap.of(NODES, nodes, ZK_PATH, zkPath));
    }

}
