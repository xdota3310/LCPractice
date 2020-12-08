package com.lr.flink.flink.mongo;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.mongodb.ServerAddress;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author chengtao
 * @date 19/11/2020 - 14:45
 */
public class MongoConfig implements Serializable {

    public static final String FN_MONGO_ADDRESS = "mongo.address";
    public static final String FN_MONGO_USER = "mongo.user";
    public static final String FN_MONGO_PASSWORD = "mongo.password";
    public static final String FN_MONGO_ADMIN_DATABASE = "mongo.database";

    private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

    private List<ServerAddress> serverAddresses;
    private String user;
    private String passwd;
    private String databaseName;
    private String bucketName;
    private String adminDataBase;

    public List<ServerAddress> getServerAddresses() {
        return serverAddresses;
    }

    public MongoConfig setServerAddresses(List<ServerAddress> serverAddresses) {
        this.serverAddresses = serverAddresses;
        return this;
    }

    public String getBucketName() {
        return bucketName;
    }

    public MongoConfig setBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public String getUser() {
        return user;
    }

    public MongoConfig setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPasswd() {
        return passwd;
    }

    public MongoConfig setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public MongoConfig setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public String getAdminDataBase() {
        return adminDataBase;
    }

    public MongoConfig setAdminDataBase(String adminDataBase) {
        this.adminDataBase = adminDataBase;
        return this;
    }

    static public MongoConfig buildFrom(Settings settings) {
        MongoConfig mongoConfig = new MongoConfig();
        mongoConfig.setUser(settings.get(FN_MONGO_USER));
        mongoConfig.setPasswd(settings.get(FN_MONGO_PASSWORD));
        mongoConfig.setAdminDataBase(settings.get(FN_MONGO_ADMIN_DATABASE));
        String address = settings.get(FN_MONGO_ADDRESS);

        mongoConfig.setServerAddresses(
                Splitter.on(",").splitToList(address).stream()
                        .map(addr -> Splitter.on(":").splitToList(addr))
                        .map(ipAndPort -> new ServerAddress(ipAndPort.get(0), Integer.valueOf(ipAndPort.get(1))))
                        .collect(Collectors.toList()));

        Preconditions.checkNotNull(settings.get(FN_MONGO_ADDRESS));
        Preconditions.checkNotNull(mongoConfig.getServerAddresses());
        Preconditions.checkNotNull(mongoConfig.getUser());
        Preconditions.checkNotNull(mongoConfig.getPasswd());
        Preconditions.checkNotNull(address);

        return mongoConfig;

    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MongoConfig.class.getSimpleName() + "[", "]")
                .add("serverAddresses=" + serverAddresses)
                .add("user='" + user + "'")
                .add("passwd='" + passwd + "'")
                .add("databaseName='" + databaseName + "'")
                .add("bucketName='" + bucketName + "'")
                .toString();
    }
}
