package com.lr.leetcode.array;

/**
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * <p>
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * <p>
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author xushijie5
 * @since 2020年04月21日
 */
public class numberOfSubarrays {
    public int numberOfSubarrays1(int[] nums, int k) {
        int size = nums.length;
        int res = 0;
        int[] oddSet = new int[size];
        int oddIndex = -1;

        for(int i = 0; i < size; i++) {
            if(isOdd(nums[i])) {
                oddSet[++oddIndex] = i;
            }
        }

        if(oddIndex + 1 < k){
            return 0;
        }

        int leftIndex = 0;
        int rightIndex = k - 1;
        while(true) {
            int oddStartIndex = oddSet[leftIndex];
            int oddEndIndex = oddSet[rightIndex];
            int leftRange = leftIndex == 0 ? oddStartIndex : oddStartIndex - oddSet[leftIndex - 1] - 1;
            int rightRange = rightIndex == oddIndex ? size - oddEndIndex - 1 : oddSet[rightIndex + 1] - oddEndIndex - 1;
            res += (leftRange + 1) * (rightRange + 1);
            if(rightIndex == oddIndex) {
                break;
            }
            leftIndex++;
            rightIndex++;
        }
        return res;
    }

    boolean isOdd(int num) {
        return (num & 1) == 1;
    }

    public static void main(String[] args) {
//        int[] a = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int[] a = {1, 1, 2, 1, 1};
        System.out.println(new numberOfSubarrays().numberOfSubarrays1(a, 3));
    }


}
