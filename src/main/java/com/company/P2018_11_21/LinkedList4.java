package com.company.P2018_11_21;

/**
 * 4.删除链表倒数第 n 个结点
 *
 * @author shijie.xu
 * @since 2018年11月28日
 */
public class LinkedList4 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p=new ListNode(-1);
        p.next=head;
        ListNode q=head;
        for(int i=1;i<n;i++){
            q=q.next;
        }
        while(q.next!=null){
            q=q.next;
            p=p.next;
        }
        if(p.next==head){
            head=head.next;
        }else {
            p.next=p.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
    }
}
