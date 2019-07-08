package com.July.Search;

import java.text.DecimalFormat;

/**
 * 二分查找
 *
 * @author shijie.xu
 * @since 2019年07月04日
 */
public class Binary {

    /**
     * 查找第一个值等于给定值的元素
     * @param a
     * @param n
     * @return
     */
    public static int pra1(int[] a,int n){
        return 0;
    }

    /**
     * 查找最后一个值等于给定值的元素
     * @param a
     * @param n
     * @return
     */
    public static int pra2(int[] a,int n){
        return 0;
    }

    /**
     * 查找第一大于等于给定值的元素
     * @param a
     * @param n
     * @return
     */
    public static int pra3(int[] a,int n){
        return 0;
    }

    /**
     * 查找最后一个值小于等于给定值的元素
     * @param a
     * @param n
     * @return
     */
    public static int pra4(int[] a,int n){
        return 0;
    }


    public static int search(int[] a, int target) {
        int length = a.length;
        int start = 0;
        int end = length - 1;
        while(start <= end) {
            int mid = ((end - start) >> 1) + start;
            if(a[mid] == target) {
                return mid;
            } else if(target < a[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static String sqort(int n) {
        DecimalFormat decimalFormat = new DecimalFormat("0.000000");
        if(n < 0) {
            return null;
        }
        if(n == 0 || n == 1) {
            return decimalFormat.format((double) n);
        }

        int start = 0;
        int end = n;
        while(start < end) {
            int mid = ((end - start) / 2) + start;
            if(mid * mid == n) {
                return decimalFormat.format((double) mid);
            } else if(start + 1 == end) {
                if(end * end == n) {
                    return decimalFormat.format((double) end);
                }


            } else if(mid * mid < n) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return decimalFormat.format((double) n);
    }

    public static boolean isNum(Double d, int n) {
        String t = d.toString();
        int index = t.indexOf(".");

        if(t.substring(index,t.length()).length() >= n){
            return true;
        }
        return false;
    }

    /**
     * leetcode 69
     * @param x
     * @return
     */
    public static int mySqrt(int x) {

        if(x == 0 || x == 1) {
            return x;
        }

        long start = 0;
        long end = x;
        while(start < end) {
            long mid = ((end - start) / 2) + start;
            long temp = mid * mid;
            if(temp == x) {
                return (int) mid;
            } else if(start + 1 == end) {
                long e = end * end;
                if(e == x) {
                    return (int) end;
                }
                return (int) start;
            } else if(temp > x) {
                end = mid;
            } else {
                start = mid;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        mySqrt(2147395599);

        Double a = 646413.46486513456500000000;
        String b = a.toString();
        System.out.println(b);
    }

}
