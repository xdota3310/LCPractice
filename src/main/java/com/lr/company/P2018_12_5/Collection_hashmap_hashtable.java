package com.company.P2018_12_5;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年12月05日
 */
public class Collection_hashmap_hashtable {
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>(16);
        Hashtable<String, String> hashtable = new Hashtable<>(16);
        hashMap.put("test1","m1");
        hashtable.put("test1","t1");
        System.out.println(hashMap.get("test1"));



    }
}
