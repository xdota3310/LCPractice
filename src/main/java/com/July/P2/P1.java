package com.July.P2;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年07月08日
 */
public class P1 {

    @Override
    public String toString() {
        return getClass().getName() + "{name: one}";
    }

    public static void get(Object obj) {
        System.out.println(obj.toString());
    }

    public static void list1() {
        Long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start + "[" + list.size() + "]");
    }

    public static void list2() {
        Long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>(100000000);
        for(int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        ((ArrayList<Integer>) list).trimToSize();
        Long end = System.currentTimeMillis();
        System.out.println(end - start + "[" + list.size() + "]");
    }

    public static void main(String[] args) {
        int[] a = new int[100];
        System.out.println(a.length);
        list1();
        list2();
    }
}
