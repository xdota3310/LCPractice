package com.lr.flink.flink;

import cn.yottabyte.flink.beaver.BeaverConfig;
import cn.yottabyte.flink.mongo.MongoConfig;
import cn.yottabyte.util.LoggerFactory;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.util.Preconditions;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.common.settings.Settings;

import java.io.File;
import java.io.IOException;

/**
 * @author chengtao
 * @date 19/11/2020 - 16:35
 */
public class FlinkJobArgsParser {

    private static Logger logger = LoggerFactory.getLogger(FlinkJobArgsParser.class);

    public static FlinkJobArgs parse(String[] args) {

        ParameterTool params = ParameterTool.fromArgs(args);
        FlinkJobArgs flinkJobArgs = new FlinkJobArgs();

        // set Setting Files
        String settingFile = params.get(FlinkJobArgs.SETTING_CONFIG_PATH);
        Preconditions.checkNotNull(settingFile);

        Settings settings;
        try {
            settings = Settings.builder().loadFromPath(new File(settingFile).toPath()).build();
            flinkJobArgs.setSettings(settings);
            flinkJobArgs.setMongoConfig(MongoConfig.buildFrom(settings));
            flinkJobArgs.setBeaverConfig(BeaverConfig.buildFromSettings(settings));
        } catch (IOException ex) {
            throw new RuntimeException("error loading " + settingFile, ex);
        }

        String absBundlePath = params.get(FlinkJobArgs.TRAIN_BUNDLE_PATH);
        Preconditions.checkNotNull(absBundlePath);
        TrainJobBundleStore bundleLoader = new TrainJobBundleStore(null);
        TrainJobBundle trainJobBundle = bundleLoader.load(absBundlePath, false);
        flinkJobArgs.setTrainJobBundle(trainJobBundle);

        Preconditions.checkNotNull(trainJobBundle);
        Preconditions.checkNotNull(trainJobBundle.getEarliest());
        Preconditions.checkNotNull(trainJobBundle.getLatest());
        Preconditions.checkNotNull(trainJobBundle.getName());
        Preconditions.checkNotNull(trainJobBundle.getIndexPrefix());
        Preconditions.checkNotNull(trainJobBundle.getQueryString());

        return flinkJobArgs;
    }

    private static String escape(String arg) {
        if (arg.length() > 1 && arg.startsWith("\"") && arg.endsWith("\"")) {
            return StringEscapeUtils.escapeJava(arg);
        }
        return arg;
    }
}
