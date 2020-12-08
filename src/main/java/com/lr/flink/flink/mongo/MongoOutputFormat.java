package com.lr.flink.flink.mongo;

import cn.yottabyte.model.Model;
import cn.yottabyte.spark.serializable.SettingsSeri;
import org.apache.flink.api.common.io.OutputFormat;
import org.apache.flink.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * @author chengtao
 * @date 17/11/2020 - 19:53
 */

public class MongoOutputFormat implements OutputFormat<Model> {

    private static final Logger logger = LoggerFactory.getLogger(MongoOutputFormat.class);

    private MongoConfig mongoConfig;
    private transient MongoKVStorage storage;
    private SettingsSeri settingsSeri;

    public MongoOutputFormat(MongoConfig mongoConfig, SettingsSeri settingsSeri) {
        this.mongoConfig = mongoConfig;
        this.settingsSeri = settingsSeri;
    }

    @Override
    public void configure(Configuration configuration) {
        // do nothing
    }

    @Override
    public void open(int taskNumber, int numTasks) throws IOException {
        storage = new MongoModelStorage();
        storage.init(mongoConfig);
    }

    @Override
    public void writeRecord(Model model) throws IOException {
        model.initForDetection(settingsSeri);
        storage.write(model.getName(), model);
    }

    @Override
    public void close() throws IOException {
        if (storage != null) {
            storage.close();
        }
    }

}
