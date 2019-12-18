package com.interview.jianzhiOffer;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月06日
 */
public class T16 {
    static double power(double base, int exponent) {
        if(base == 0d) {
            return 0d;
        }
        boolean flag = exponent < 0;
        long exponentLong = exponent;
        exponentLong = flag ? -exponentLong : exponentLong;
        double res = doPower(base, exponentLong);
        if(flag) {
            res = 1 / res;
        }
        return res;
    }

    static double doPower(double base, long exponent) {
        if(exponent == 0) {
            return 1;
        }
        double res = doPower(base, exponent >> 1);
        if((exponent & 1) == 1) {
            return res * res * base;
        } else {
            return res * res;
        }
    }

    public static void main(String[] args) {
        System.out.println(power(1d, 0x80000000));
    }
}
