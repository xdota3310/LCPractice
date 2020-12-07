package com.lr.leetcode.linkedList;

import com.lr.leetcode.base.ListNode;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author xu.shijie
 * @since 11/30/20
 */
public class ReversePrint {
    Stack<Integer> stack = new Stack<>();

    public int[] reversePrint(ListNode head) {
        doRec(head);
        int len = stack.size();
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    private void doRec(ListNode head) {
        if (head == null) {
            return;
        }
        stack.push(head.val);
        doRec(head.next);
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;

        int[] res = new ReversePrint().reversePrint(node1);
        System.out.println();
        HashSet<Integer> set = new HashSet<>();
    }
}
