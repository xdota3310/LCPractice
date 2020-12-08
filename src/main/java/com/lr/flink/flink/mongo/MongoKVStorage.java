package com.lr.flink.flink.mongo;

import cn.yottabyte.flink.KVStorage;
import com.google.common.base.Preconditions;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import org.elasticsearch.common.io.stream.OutputStreamStreamOutput;
import org.elasticsearch.common.io.stream.Writeable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author chengtao
 * @date 24/11/2020 - 20:52
 */
public class MongoKVStorage<T extends Writeable> implements KVStorage<T> {

    private static final Logger logger = LoggerFactory.getLogger(MongoModelStorage.class);

    private transient MongoClient mongoClient;
    private transient MongoDatabase mongoDatabase;
    private transient GridFSBucket gridFSBucket;

    public void init(MongoConfig mongoConfig) {
        Preconditions.checkNotNull(mongoConfig);
        Preconditions.checkNotNull(mongoConfig.getServerAddresses());
        Preconditions.checkNotNull(mongoConfig.getDatabaseName());
        Preconditions.checkNotNull(mongoConfig.getPasswd());
        Preconditions.checkNotNull(mongoConfig.getUser());

        MongoCredential credential = MongoCredential.createCredential(
                mongoConfig.getUser(),
                mongoConfig.getAdminDataBase(),
                mongoConfig.getPasswd().toCharArray());

        logger.info("mongo config {}", mongoConfig);

        this.mongoClient = new MongoClient(mongoConfig.getServerAddresses(), Arrays.asList(credential));
        this.mongoDatabase = mongoClient.getDatabase(mongoConfig.getDatabaseName());
        this.gridFSBucket = GridFSBuckets.create(mongoDatabase, mongoConfig.getBucketName());

    }

    @Override
    public void write(String key, T t) {
        // TODO: remove old value
        logger.info("write it to mongo {}", key);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             OutputStreamStreamOutput output = new OutputStreamStreamOutput(baos);
             GridFSUploadStream uploadStream = gridFSBucket.openUploadStream(key)) {
            t.writeTo(output);
            uploadStream.write(baos.toByteArray());
        } catch (IOException e) {
            logger.error("error write to mongo {} ", key, e);
            throw new RuntimeException("Error write to mongo " + key);
        }
    }

    @Override
    public T read(String key) {
        return null;
    }

    @Override
    public void update(String key, T t) {
    }

    @Override
    public void delete(String key) {
    }

    @Override
    public void close() {
        mongoClient.close();
    }

}
