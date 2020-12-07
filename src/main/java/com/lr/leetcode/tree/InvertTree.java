package com.lr.leetcode.tree;

import com.lr.leetcode.base.TreeNode;

/**
 * 翻转一棵二叉树。
 *
 * From
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * To
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * @author shijie.xu
 * @since 2019年10月30日
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if(root ==null){
            return null;
        }
        TreeNode l = invertTree(root.left);
        TreeNode r = invertTree(root.right);
        root.left = r;
        root.right = l;
        return root;
    }
}
