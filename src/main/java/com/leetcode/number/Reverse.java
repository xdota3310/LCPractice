package com.leetcode.number;

/**
 * #7
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * @author shijie.xu
 * @since 2019年12月01日
 */
public class Reverse {
    public int reverse(int x) {
        long res = 0;
        while(x != 0) {
            int m = x % 10;
            x = x / 10;
            res = res * 10 + m;
        }
        if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(new Reverse().reverse(-16786413));
    }
}
