package com.lr.leetcode.linkedList;

import com.lr.leetcode.base.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 *
 * Could you do it in O(n) time and O(1) space?
 *
 * @author shijie.xu
 * @since 2019年09月05日
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        if(head == null || head.next == null) {
            return true;
        }
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow;
        if(fast != null) {
            slow = slow.next;
        }
        ListNode nHead = null;
        ListNode p;
        while(slow != null) {
            p = slow;
            slow = slow.next;
            p.next = nHead;
            nHead = p;
        }
        while(nHead != null) {
            if(nHead.val != head.val) {
                return false;
            }
            nHead = nHead.next;
            head = head.next;
        }
        if(head == mid) {
            return true;
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        ListNode l = null;
        for(int i = 0; i < 6; i++) {
            ListNode t = new ListNode(1);
            t.next = l;
            l=t;
        }
        new IsPalindrome().isPalindrome(l);

    }
}
