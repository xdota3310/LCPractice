package com.company.P2018_11_21;

/**
 * 两个有序的链表合并
 *
 * @author shijie.xu
 * @since 2018年11月27日
 */
public class LinkedList3 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=null;
        ListNode p =head;
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        while(l1!=null&&l2!=null){
            if(l1.data<=l2.data){
                if(head==null){
                    head=l1;
                    p=head;
                }else{
                    p.next=l1;
                    p=p.next;
                }
                l1=l1.next;
            }else{
                if(head==null){
                    head=l2;
                    p=head;
                }else{
                    p.next=l2;
                    p=p.next;
                }
                l2=l2.next;
            }
        }
        if(l1==null&&l2!=null){
            p.next=l2;
        }
        if(l2==null){
            p.next=l1;
        }
        return head;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head=null;
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        if(l1.data<=l2.data){
            head=l1;
            head.next=mergeTwoLists(l1.next,l2);
        }else{
            head=l2;
            head.next=mergeTwoLists(l1,l2.next);
        }
        return head;
    }



    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        l1.next=new ListNode(2);
        l1.next.next=new ListNode(4);
        ListNode l2=new ListNode(1);
        l2.next=new ListNode(3);
        l2.next.next=new ListNode(4);
        mergeTwoLists(l1,l2);
    }
}
