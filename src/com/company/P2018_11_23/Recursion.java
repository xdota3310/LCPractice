package com.company.P2018_11_23;

/**
 * 假如这里有 n 个台阶，每次你可以跨 1 个台阶或者 2 个台阶，请问走这 n 个台阶有多少种走法？
 * 如 果有 7 个台阶，你可以 2，2，2，1 这样子上去，也可以 1，2，1，1，2 这样子上去，总之走法有很多，
 * 那如何用编程求得总共有多少种走法呢
 * 递归、非递归实现
 *
 * 斐波那契数列
 *
 *
 * @author shijie.xu
 * @since 2018年11月23日
 */
public class Recursion {
    public static int rec1(int n){
        if(n==1){
            return 1;

        }
        if(n<=0){
            return 1;
        }
        return rec1(n-1)+rec1(n-2);
    }

    public static int rec2(int n){
        int a=1;
        int b=1;
        int c=0;
        if(n==0){
            return a;
        }
        if(n==1){
            return b;
        }
        for(int i=1;i<n;i++){
            c=a+b;
            a=b;
            b=c;
        }
        return c;
    }

    public static void main(String[] args) {
        int n=10;
        System.out.println(rec1(n));
        System.out.println(rec2(n));
    }

}
