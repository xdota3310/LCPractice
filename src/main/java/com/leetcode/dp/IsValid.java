package com.leetcode.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author xu.shijie
 * @since 2020.08.14
 */
public class IsValid {
    private static final Map<Character, Character> MAP = new HashMap<Character, Character>() {{
        put('(', ')');
        put('[', ']');
        put('{', '}');
    }};

    public static boolean isVal(String s) {
        if(s == null || "".equals(s)) {
            return true;
        }
        int length = s.length();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < length; i++) {
            Character cur = s.charAt(i);
            if(!stack.isEmpty()){
                Character c = stack.peek();
                if(cur.equals(MAP.get(c))) {
                    stack.pop();
                } else {
                    stack.push(cur);
                }
            }else {
                stack.push(cur);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isVal("()[]{}"));
    }
}
