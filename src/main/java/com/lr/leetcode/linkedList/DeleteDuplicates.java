package com.lr.leetcode.linkedList;

import com.lr.leetcode.base.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 *
 * 83
 *
 * @author shijie.xu
 * @since 2019年09月05日
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        while(node.next != null) {
            if(node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }
}
