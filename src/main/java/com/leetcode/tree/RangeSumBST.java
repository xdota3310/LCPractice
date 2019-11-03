package com.leetcode.tree;

import com.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
 * 二叉搜索树保证具有唯一的值
 *
 * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 输出：32
 *
 * @author shijie.xu
 * @since 2019年10月21日
 */
public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        int[] sum = {0};
        sum(root, L, R, sum);
        return sum[0];
    }

    void sum(TreeNode root, int L, int R, int[] sum) {
        if(root != null) {
            if(root.val >= L && root.val <= R) {
                sum[0] += root.val;
            }
            if(root.val >= L) {
                sum(root.left, L, R, sum);
            }
            if(root.val <= R) {
                sum(root.right, L, R, sum);
            }
        }
    }

    void bfs(TreeNode root, int L, int R, int[] sum) {
        if(root != null) {

        }
    }
}
