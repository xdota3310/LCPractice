package com.company.P2018_11_30;

import java.util.BitSet;

/**
 * 求 小于200W的素数的个数
 *
 * @author shijie.xu
 * @since 2018年11月30日
 */
public class Test {
    public static void t1() {
        int count = 0;
        int n = 2000000;
        Long start = System.currentTimeMillis();
        // 1不是素数，所以直接从2开始循环
        for(int i = 2; i <= n; i++) {
            int j = 2;
            while(i % j != 0) {
                j++; // 测试2至i的数字是否能被i整除，如不能就自加
            }
            if(j == i) {
                //当有被整除的数字时，判断它是不是自身,若是，则说明是素数
                // 如果是就打印出数字
//                System.out.println(i);
                count++;
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println(count + " primes");
        System.out.println(end - start+" ms");
    }

    public static void t2() {
        int n = 2000000;
        Long start = System.currentTimeMillis();
        BitSet b = new BitSet(n + 1);
        int count = 0;
        int i = 2;
        for(; i <= n; i++) {
            b.set(i);
        }
        i = 2;
        while(i * i <= n) {
            if(b.get(i)) {
                count++;
                int k = 2 * i;
                while(k <= n) {
                    b.clear(k);
                    k += i;
                }
            }
            i++;
        }
        while(i <= n) {
            if(b.get(i)) {
//                System.out.println(i);
                count++;
            }
            i++;
        }
        Long end = System.currentTimeMillis();
        System.out.println(count + " primes");
        System.out.println(end - start+" ms");
    }


    public static void main(String[] args) {
        /**
         * 测试范围：2~2000000
         * t1的运行时间：约372954 ms
         * t2的运行时间：约38 ms
         * */
        t1();
        t2();
    }
}
