package com.company.P2018_12_27;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年04月10日
 */
public class Tree2 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    LinkedList<Integer> list = new LinkedList<>();

    public Tree2(TreeNode root) {
        node(root);
    }

    private void node(TreeNode node) {
        if(node != null) {
            node(node.left);
            list.addLast(node.val);
            node(node.right);
        }
    }

    private void node2(TreeNode node) {
//        LinkedList<Integer> list = new LinkedList<>();
//        HashSet<TreeNode> visited = new HashSet<>();
        Stack<TreeNode> tobeVisit = new Stack<>();
        TreeNode current = node;
        while(current != null || !tobeVisit.isEmpty()) {
            while(current != null) {
                tobeVisit.push(current);
                current = current.left;
            }
            current = tobeVisit.pop();
            list.addLast(current.val);
//            visited.add(current);
            current = current.right;
        }
    }

    /** @return the next smallest number */
    public int next() {
        return list.removeFirst();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !list.isEmpty();
    }
}
