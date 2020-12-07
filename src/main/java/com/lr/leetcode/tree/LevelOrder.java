package com.lr.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * @author shijie.xu
 * @since 2019年11月02日
 */
public class LevelOrder {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        if(root == null){
            return list;
        }
        queue.add(root);
        List<Integer> list1 = new ArrayList<>();
        list1.add(root.val);
        list.add(list1);
        while(queue.size()>0){
            List<Integer> Tlist = new ArrayList<>();
            Queue<Node> Tqueue = new ArrayDeque<>();
            Node node = queue.poll();
            while(node != null){
                for(Node q: node.children){
                    Tqueue.add(q);
                    Tlist.add(q.val);
                }
                node = queue.poll();
            }
            list.add(Tlist);
            queue = Tqueue;
        }
        return list;
    }


}
