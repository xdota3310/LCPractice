package com.April.q4_18;

import java.util.HashMap;

/**
 * 创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：
 *
 * 1. set(string key, string value, int timestamp)
 *
 * 存储键 key、值 value，以及给定的时间戳 timestamp。
 * 2. get(string key, int timestamp)
 *
 * 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
 * 如果有多个这样的值，则返回对应最大的  timestamp_prev 的那个值。
 * 如果没有值，则返回空字符串（""）。
 *
 * @author shijie.xu
 * @since 2019年04月18日
 */
public class TimeMap {
    static HashMap<String, HashMap> map = new HashMap<>();

    /** Initialize your data structure here. */
    public TimeMap() {

    }

    public static void set(String key, String value, int timestamp) {
        HashMap<String, Object> temp = new HashMap<>(1);
        Integer v = Integer.parseInt(value);
        temp.put("value", v);
        temp.put("timestamp", timestamp);
        if(!map.isEmpty()) {
            HashMap hashMap = map.get(key);
            if(hashMap != null) {
                if((Integer) hashMap.get("value") <= v && timestamp >= (int) hashMap.get("timestamp")) {
                    map.put(key, temp);
                }
            } else {
                map.put(key, temp);
            }

        }
    }

    public String get(String key, int timestamp) {
        HashMap hashMap = map.get(key);
        if(hashMap == null || hashMap.isEmpty()) {
            return hashMap.get("").toString();
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        set("1", "2", 1);
        set("1", "3", 1);
        set("1", "4", 2);
        System.out.println(map);
    }
}
