package com.lr.leetcode.array;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年09月10日
 */
public class FlipAndInvertImage {
    public int[][] flipAndInvertImage(int[][] A) {
        for(int i = 0; i < A.length; i++) {
            reverse(A[i]);
            swap(A[i]);
        }
        return A;
    }


    private void reverse(int[] a) {
        int l = a.length - 1;
        for(int i = 0; i < l; i++) {
            int t = a[i];
            a[i] = a[l];
            a[l] = t;
            l--;
        }
    }

    private void swap(int[] a) {
        for(int i = 0; i < a.length; i++) {
            if(a[i] == 1) {
                a[i] = 0;
            } else {
                a[i] = 1;
            }
        }
    }


    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4, 5}, {4, 5, 6, 7, 8}};
        new FlipAndInvertImage().flipAndInvertImage(a);
    }
}
