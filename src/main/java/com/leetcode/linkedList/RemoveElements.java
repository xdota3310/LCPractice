package com.leetcode.linkedList;

import com.leetcode.base.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 *
 * @author shijie.xu
 * @since 2019年09月05日
 */
public class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) {
            return head;
        }
        while(head != null && head.val == val) {
            head = head.next;
        }
        ListNode p = head;
        ListNode q = new ListNode(-1);
        q.next = head;
        while(p != null) {
            if(p.val == val) {
                q.next = p.next;
                p = q.next;
            } else {
                p = p.next;
                q = q.next;
            }
        }
        return head;
    }
}
