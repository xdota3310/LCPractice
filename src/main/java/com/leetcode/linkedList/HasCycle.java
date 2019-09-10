package com.leetcode.linkedList;

import com.leetcode.base.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * @author shijie.xu
 * @since 2019年09月06日
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }
}
