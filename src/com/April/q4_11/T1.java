package com.April.q4_11;

/**
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 说明 :
 *
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 *
 * @author shijie.xu
 * @since 2019年04月11日
 */
public class T1 {
    public static int findUnsortedSubarray(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i + 1] < nums[i] || is2(start, end, nums)) {

                start = i;
            }
            if(nums[nums.length - i - 1] < nums[nums.length - i - 2] || is1(start, end, nums)) {
                end = nums.length - i - 1;
            }
        }
        if(start < end ) {
            return end - start + 1;
        } else {
            return 0;
        }

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
        int[] test = {1, 1};
        findUnsortedSubarray(test);
        System.out.println(test);
    }
}
