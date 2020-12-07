package com.lr.leetcode.array;

/**
 * Students are asked to stand in non-decreasing order of heights for an annual photo.
 *
 * Return the minimum number of students not standing in the right positions. 
 * (This is the number of students that must move in order for all students to be standing
 * in non-decreasing order of height.)
 *
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/height-checker
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shijie.xu
 * @since 2019年09月10日
 */
public class HeightChecker {
    public int heightChecker(int[] heights) {
        int[] count = new int[101];
        int total = 0;
        for(int h : heights) {
            count[h]++;
        }
        for(int i = 0, j = 0; i < count.length; i++) {
            while(count[i]-- > 0) {
                if(heights[j] != i){
                    total++;
                }
                j++;
            }
        }
        return total;
    }
}
