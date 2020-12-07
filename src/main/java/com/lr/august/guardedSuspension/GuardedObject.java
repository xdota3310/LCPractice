package com.august.guardedSuspension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Guarded Suspension，直译过来就是“保护性地暂停”
 *
 * @author shijie.xu
 * @since 2019年08月27日
 */
public class GuardedObject<T> {

    /**
     * 受保护的对象
     */
    private T t;
    private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();
    private final int timeout = 1;

    private static Map<Object, GuardedObject> map = new ConcurrentHashMap<>();

    private GuardedObject() {
    }

    static <K> GuardedObject get(K k) {
        if(map.containsKey(k)) {
            throw new RuntimeException("请使用唯一标识：" + k.toString());
        }
        GuardedObject object = new GuardedObject<>();
        map.put(k, object);
        return object;
    }

    static <K, T> void doIt(K k, T t) {
        if(!map.containsKey(k)) {
            throw new RuntimeException("无效的标识：" + k.toString());
        }
        map.get(k).onChange(t);
    }


    /**
     * 获取受保护的对象
     *
     * @param predicate
     * @return
     */
    T get(Predicate<T> predicate) {
        lock.lock();
        try {
            while(!predicate.test(t)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    /**
     * 事件通知
     *
     * @param obj
     */
    void onChange(T obj) {
        lock.lock();
        try {
            t = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
