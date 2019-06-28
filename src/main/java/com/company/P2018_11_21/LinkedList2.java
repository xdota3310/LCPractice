package com.company.P2018_11_21;

/**
 * 2.链表中环的检测
 *
 * @author shijie.xu
 * @since 2018年11月26日
 */
public class LinkedList2 {
    public static boolean check(ListNode head) {
        ListNode fast;
        ListNode slow;

        if(head == null || head.next == null || head.next.next == null) {
            return false;
        }
        fast = head.next;
        slow = head;
        while(fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == null || fast.next == null) {
                return false;
            }

        }
        return true;
    }
}
