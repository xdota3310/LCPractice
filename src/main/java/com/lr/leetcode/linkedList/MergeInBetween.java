package com.lr.leetcode.linkedList;

import com.lr.leetcode.base.ListNode;

/**
 * @author xu.shijie
 * @since 12/8/20
 */
public class MergeInBetween {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode preA = getNodeByIndex(list1, a);
        ListNode preB = getNodeByIndex(list1, b);
        ListNode tail = list2;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = preB.next.next;
        preA.next = list2;
        return list1;
    }

    private ListNode getNodeByIndex(ListNode head, int index) {
        for (int i = 1; i < index; i++) {
            if (head != null) {
                head = head.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        l1.next = l2;
        l2.next = l3;
        ListNode l4 = new ListNode(100);
        ListNode l5 = new ListNode(101);
        ListNode l6 = new ListNode(102);
        l4.next = l5;
        l5.next = l6;
        new MergeInBetween().mergeInBetween(l1, 1, 1, l4);
        System.out.println();
    }
}
