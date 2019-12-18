package com.June;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年06月04日
 */
public class P1 {

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    class LRUCache {
        private int capacity;
        private Map<Integer, Integer> cache;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                // 定义put后的移除规则，大于容量就删除eldest
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };

            Map<Object,Object> as = new HashMap<Object,Object>(){
                @Override
                public Collection<Object> values() {

                    return null;
                }
            };
        }

        public int get(int key) {
            if(cache.containsKey(key)) {
                return cache.get(key);
            } else {
                return -1;
            }
        }

        public void set(int key, int value) {
            cache.put(key, value);
        }
    }

    public static void main(String[] args) {
//        String str1 = "a";
//        String str2 = "b";
//        String str3 = "ab";
//        String str4 = str1 + str2;
//        String str5 = new String("ab");
//
//        System.out.println(str5.equals(str3));
//        System.out.println(str5 == str3);
//        System.out.println(str5.intern() == str3);
//        System.out.println(str3 == str4);
//        System.out.println(str5.intern() == str4);

        String a = new String("ab");
        String b = new String("ab");
        String c = "ab";
        String d = "a" + "b";
        String e = "b";
        String f = "a" + e;

        System.out.println(b == a);
        System.out.println(b.intern() == a);
        System.out.println(b.intern() == c);
        System.out.println(b.intern() == d);
        System.out.println(b.intern() == f);
        System.out.println(b.intern() == a.intern());
        tableSizeFor(10);
    }
}
