package com.lr.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xu.shijie
 * @since 2020/11/3
 */
public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> arr1 = new HashSet<>();
        if (nums1 == null || nums2 == null) {
            return null;
        }
        for (int num1 : nums1) {
            arr1.add(num1);
        }
        int[] resArr = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        for (int num2 : nums2) {
            if (arr1.contains(num2)) {
                resArr[index++] = num2;
            }
        }
        return resArr;
    }
}
