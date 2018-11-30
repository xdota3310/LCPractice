package com.company.P2018_11_21;

/**
 * 求链表的中间结点
 *
 * @author shijie.xu
 * @since 2018年11月28日
 */
public class LinkedList5 {
    public ListNode middleNode(ListNode head) {
        ListNode p = head;
        ListNode q = head;
//        if(head == null || head.next == null) {
//            return head;
//        }
        if(head.next.next == null) {
            return head.next;
        }
        while(q.next != null && q.next.next != null) {
            q=q.next.next;
            p=p.next;
        }
        if(q.next==null){
            p.next=null;
            return p;
        }else if(q.next.next==null){
            p=p.next;
            p.next=null;
            return p;
        }
        return head;
    }
}
