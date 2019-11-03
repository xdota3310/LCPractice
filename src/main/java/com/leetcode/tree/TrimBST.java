package com.leetcode.tree;

import com.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trim-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shijie.xu
 * @since 2019年11月02日
 */
public class TrimBST {
    List<TreeNode> list = new ArrayList<>();

    public TreeNode trimBST(TreeNode root, int L, int R) {
        first(root);
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).val < L || list.get(i).val > R) {
                list.remove(i);
            }
        }
        return tree(list, 0, list.size() - 1);
    }

    void first(TreeNode root) {
        if(root == null) {
            return;
        }
        first(root.left);
        list.add(root);
        first(root.right);
    }

    TreeNode tree(List<TreeNode> nodes, int start, int end) {
        System.out.println(start);
        System.out.println(end);
        if(nodes == null || start > end) {
            return null;
        }
        int mid = (start + end) >>> 1;
        TreeNode node = nodes.get(mid);
        node.left = tree(nodes, start, mid - 1);
        node.right = tree(nodes, mid + 1, end);
        return node;
    }

}
