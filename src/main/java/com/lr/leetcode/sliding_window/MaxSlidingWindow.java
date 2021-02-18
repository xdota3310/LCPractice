package com.lr.leetcode.sliding_window;

/**
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 * <p>
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 * @author xu.shijie
 * @since 2021/2/18
 */
public class MaxSlidingWindow {
    int[] heap;

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null) {
            return null;
        }
        int[] res = new int[nums.length - k + 1];

        heap = new int[k];
        return heap;

    }

    private void push(int val) {
        int start = 0;
        int end = heap.length;
        int child;
        int rootVal = heap[start];
        if (val <= rootVal) {
            return;
        }

        for (; start < end; start++) {
            child = start * 2 + 1;
            if (child < end && child + 1 < end) {
                if (heap[child + 1] < heap[child]) {
                    child++;
                }
            }
            if (val > heap[child]) {
                heap[child] = val;
            }
        }

    }

}
