package com.august.cachee;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 线程安全的缓存
 * 读多写少
 *
 * @author shijie.xu
 * @since 2019年08月21日
 */
public class CacheE<K, V> {
    private static int CACHESIZE = 16;
    private Map<K, V> map;

    final ReadWriteLock lock = new ReentrantReadWriteLock();
    final Lock rlock = lock.readLock();
    final Lock wlock = lock.writeLock();

    private CacheE() {
        cacheInit(CACHESIZE);
    }

    private CacheE(int size) {
        cacheInit(size);
    }

    private void cacheInit(int size) {
        this.map = new HashMap<>(size);
    }

    V get(K key) {
        rlock.lock();
        try {
            return map.get(key);
        } finally {
            rlock.unlock();
        }
    }

    void put(K key, V v) {
        wlock.lock();
        try {
            map.put(key, v);
        } finally {
            wlock.unlock();
        }
    }
}
