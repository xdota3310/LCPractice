package com.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * #3
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author shijie.xu
 * @since 2019年12月01日
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int res = 0;
        Map<Character, Integer> map = new HashMap<>(n);
        for(int i = 0, j = 0; n - i > res && i < n && j < n; j++) {
            char c = s.charAt(j);
            if(map.containsKey(c)) {
                i = Math.max(map.get(c), i);
            }
            map.put(c, j + 1);
            res = Math.max(res,j - i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb"));
    }
}
