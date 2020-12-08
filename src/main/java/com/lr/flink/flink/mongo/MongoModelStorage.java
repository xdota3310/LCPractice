package com.lr.flink.flink.mongo;

import cn.yottabyte.model.Model;
import cn.yottabyte.model.ModelServer;

/**
 * @author chengtao
 * @date 19/11/2020 - 14:58
 */
public class MongoModelStorage extends MongoKVStorage<Model> {

    @Override
    public void init(MongoConfig mongoConfig) {
        mongoConfig.setDatabaseName(ModelServer.MODEL_SERVER_DATABASE_NAME);
        mongoConfig.setBucketName(ModelServer.DETECTOR_COLLECTION_NAME);
        super.init(mongoConfig);
    }
}
