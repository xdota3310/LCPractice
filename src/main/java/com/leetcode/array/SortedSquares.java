package com.leetcode.array;

/**
 * Given an array of integers A sorted in non-decreasing order, 
 * return an array of the squares of each number, also in sorted non-decreasing order.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shijie.xu
 * @since 2019年09月11日
 */
public class SortedSquares {
    public int[] sortedSquares(int[] A) {
        int[] newArray = new int[A.length];
        for(int i = 0; i < A.length; i++) {
            insert(newArray, (int) Math.pow(A[i],2),i);
        }
        return newArray;
    }

    private void insert(int[] arry, int number,int j) {
        if(j==0){
            arry[j] = number;
        }else {
            int i;
            for(i = j-1; i >= 0; i--) {
                if(number <= arry[i]) {
                    arry[i + 1] = arry[i];
                }else {
                    arry[i + 1] = number;
                    return;
                }
            }
            arry[0] = number;
            return;
        }
    }

    public static void main(String[] args) {
        int[] t ={-4,-1,0,3,10};
        new SortedSquares().sortedSquares(t);
    }
}
