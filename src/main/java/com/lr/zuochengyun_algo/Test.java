package com.lr.zuochengyun_algo;
public class Test {

    public static int add(int i, int j) {
        int a = i ^ j;
        int b = (i & j) << 1;
        while (b != 0) {
            i = a;
            j = b;
            a = i ^ j;
            b = (i & j) << 1;
        }
        return a;
    }

    public static int minus(int i, int j) {
        return add(i, add(~j, 1));
    }

    public static int multiply1(int i, int j) {
        int res = 0;
        boolean sign = (i >>> 31 ^ j >>> 31) == 0;
        if ((i & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            i = minus(0, i);
        }
        if ((j & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            j = minus(0, j);
        }

        for (int k = 0; k < j; k = add(k, 1)) {
            res = add(res, i);
        }
        return sign ? res : minus(0, res);
    }

    public static int multiply2(int a, int b) {
//        1101；
//        1011；
        boolean sign = (a >>> 31 ^ b >>> 31) == 0;
        if ((a & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            a = minus(0, a);
        }
        if ((b & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            b = minus(0, b);
        }

        int k = 0;
        int res = 0;
        while (b != 0) {
            int br = b & 1;
            b = b >>> 1;
            if (br != 0) {
                res = add(res, a << k);
            }
            k = add(k, 1);
        }

        return sign ? res : minus(0, res);
    }

    public static int divide(int a, int b) {
        boolean sign = (a >>> 31 ^ b >>> 31) == 0;
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (a == Integer.MIN_VALUE) {
            if (b < 0) {
                a = minus(a, b);
            } else {
                a = add(a, b);
            }
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        }

        if (a < 0) {
            a = minus(0, a);
        }

        if (b < 0) {
            b = minus(0, b);
        }

        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if (a >> i >= b) {
                res |= 1 << i;
                a = minus(a, multiply2(b, 1 << i));
            }
        }
        return sign ? res : minus(0, res);
    }


    public static void main(String[] args) {
        System.out.println(add(2, 3));
        System.out.println(add(0, -1));
        System.out.println(add(-1, 3));
        System.out.println(add(-1, -3));
        System.out.println("----");
        System.out.println(minus(0, 1));
        System.out.println(minus(3, 2));
        System.out.println(minus(2, 3));
        System.out.println(minus(2, -3));
        System.out.println(minus(-2, -3));
        System.out.println(minus(0, -3));
        System.out.println("乘法1");
        System.out.println(multiply1(1, 2));
        System.out.println(multiply1(-1, 2));
        System.out.println(multiply1(1, -2));
        System.out.println(multiply1(-1, -2));
        System.out.println(multiply1(0, -2));
        System.out.println(multiply1(-1, 0));
        System.out.println(multiply1(0, 0));
        System.out.println(multiply1(1, Integer.MIN_VALUE));
        System.out.println("乘法2");
        System.out.println(multiply2(1, 2));
        System.out.println(multiply2(-1, 2));
        System.out.println(multiply2(1, -2));
        System.out.println(multiply2(-1, -2));
        System.out.println(multiply2(0, -2));
        System.out.println(multiply2(-1, 0));
        System.out.println(multiply2(0, 0));
        System.out.println(multiply2(1, Integer.MIN_VALUE));
        System.out.println(Integer.MIN_VALUE);
        System.out.println("除法1");
        System.out.println(divide(7, 3));
        System.out.println(divide(7, -3));
        System.out.println(divide(-7, -3));
        System.out.println(divide(Integer.MIN_VALUE, -1));
        System.out.println(divide(Integer.MIN_VALUE, -3) + " " + Integer.MIN_VALUE / -3);
    }
}
