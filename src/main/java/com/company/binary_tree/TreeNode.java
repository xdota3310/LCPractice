package com.company.binary_tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 二叉树
 *
 * @author shijie.xu
 * @since 2019年01月23日
 */
public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }


    private Deque<Integer> queue = new ArrayDeque<>();

    public int ping(int t) {
        while(!queue.isEmpty()) {
            Integer temp = queue.peekFirst();
            if(temp < t - 3000) {
                queue.pollFirst();
            }
        }
        queue.offerLast(t);
        return queue.size();
    }

    /**
     * 938. 二叉搜索树的范围和
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
//    public int rangeSumBST(TreeNode root, int L, int R) {
//        TreeNode pL = root;
//        TreeNode pR = root;
//        int res = 0;
//        while(pL != null) {
//            if()
//        }
//    }


    public class Inner {
    }

    public static boolean isMatch(String text, String pattern) {
        if(pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if(pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        String s = "mississippi";
        String p = "mis*is*p*.";
        isMatch(s, p);
    }

}
