package com.April.q4_11;

import java.util.Stack;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年04月11日
 */
public class T2 {
    public static int findUnsortedSubarray(int[] nums) {
        if(nums.length <= 0) {
            return 0;
        }
        Stack<Integer> startS = new Stack<>();
        Stack<Integer> endS = new Stack<>();
        int start = nums.length - 1;
        int end = 0;
        startS.push(0);
        endS.push(nums.length - 1);
        for(int i = 1; i < nums.length; i++) {

            if(nums[i] >= nums[startS.peek()] && is2(startS.peek(), endS.peek(), nums)) {
                if(startS.peek() <= endS.peek()) {
                    startS.push(i);
                }
            }

            if(nums[nums.length - 1 - i] <= nums[endS.peek()] && is1(startS.peek(), endS.peek(), nums)) {
                if(startS.peek() <= endS.peek()) {
                    endS.push(nums.length - 1 - i);
                }
            }
        }
        return endS.peek() - startS.peek() + 1;
    }

    private static boolean is1(int start, int end, int[] nums) {
        if(start != -1 && end != -2) {
            for(int i = start; i <= end; i++) {
                if(nums[end] < nums[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    private static boolean is2(int start, int end, int[] nums) {
        if(start != -1 && end != -2) {
            for(int i = start; i <= end; i++) {
                if(nums[start] > nums[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        int[] test = {1, 3,3,3,2};
        findUnsortedSubarray(test);
        System.out.println(test);
    }
}
