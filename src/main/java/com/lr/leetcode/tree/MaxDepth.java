package com.lr.leetcode.tree;

import com.lr.leetcode.base.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * @author shijie.xu
 * @since 2019年10月31日
 */
public class MaxDepth {
    Stack<TreeNode> stack = new Stack<>();

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }


}
