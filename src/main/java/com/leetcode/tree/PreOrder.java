package com.leetcode.tree;

import com.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xu.shijie
 * @since 2020/10/27
 */
public class PreOrder {
    List<Integer> list = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        doPreorderRec(root);
        return list;
    }

    private void doPreorderRec(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        doPreorderRec(root.left);
        doPreorderRec(root.right);
    }

    private void doPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode treeNode = stack.pop();
            list.add(treeNode.val);
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }
}
