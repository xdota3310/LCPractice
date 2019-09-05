package com.leetcode.linkedList;

import com.leetcode.base.ListNode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author shijie.xu
 * @since 2019年09月04日
 */
public class ReverseList {
    /**
     * 迭代
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode res = null;
        if(head == null) {
            return null;
        }

        while(head != null) {
            ListNode p = head;
            head = head.next;
            p.next = res;
            res = p;

        }

        return res;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode node = reverseList2(head.next);
        head.next.next = head;
        head.next =  null;
        return node;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        for(int i = 1; i < 5; i++) {
            ListNode l = new ListNode(i);
            l.next = head;
            head = l;
        }
        new ReverseList().reverseList(head);
    }
}
