package com.company.P2018_12_27;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年04月10日
 */
public class Tree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if(root != null) {
            node(root);
        }
        return sum;
    }

    public void node(TreeNode node) {
        if(node.left != null) {
            if(node.left.left == null && node.left.right == null) {
                sum += node.left.val;
            } else {
                node(node.left);
            }
        }
        if(node.right != null) {
            if(node.right.left != null || node.right.right != null) {
                node(node.right);
            }
        }
    }
}
