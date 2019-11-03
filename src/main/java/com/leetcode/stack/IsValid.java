package com.leetcode.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * @author shijie.xu
 * @since 2019年10月10日
 */
public class IsValid {
    HashMap<String, String> map = new HashMap<>();
    public IsValid() {
        map.put("}", "{");
        map.put("]", "[");
        map.put(")", "(");
    }

    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        if(s == null) {
            return false;
        }
        for(int i = 0; i < s.length(); i++) {
            String cur = s.substring(i, i + 1);
            if(!map.containsKey(cur)) {
                stack.push(cur);
            } else {
                try {
                    String t = stack.pop();
                    if(!map.get(cur).equals(t)) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        if(stack.empty()) {
            return true;
        }
        return false;
    }
}
