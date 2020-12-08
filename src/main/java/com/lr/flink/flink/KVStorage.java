package com.lr.flink.flink;

import org.elasticsearch.common.io.stream.Writeable;

/**
 * @author chengtao
 * @date 19/11/2020 - 14:23
 */
public interface KVStorage<T extends Writeable> {
    void write(String key, T t);
    T read(String key);
    void update(String key, T t);
    void delete(String key);
    void close();
}
