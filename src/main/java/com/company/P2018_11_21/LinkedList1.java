package com.company.P2018_11_21;

/**
 * 1.单链表反转
 * 2.链表中环的检测
 * 3.两个有序的链表合并
 * 4.删除链表倒数第 n 个结点
 * 5.求链表的中间结点
 *
 * Leetcode:206，141，21，19，876
 *
 * @author shijie.xu
 * @since 2018年11月21日
 *
 * 警惕指针丢失、内存泄漏、利用哨兵简化实现难度、重点留意边界条件处理，以及举例画图、辅助思考，还有多写多练
 */
public class LinkedList1 {

    public static ListNode re1(ListNode head) {
        int flag = 1;
        ListNode p;
        ListNode q;
        ListNode head1 = new ListNode(-1);

        while(head != null && head.next != null) {
            p = head;
            q = head.next;
            while(q.next != null) {
                p = q;
                q = q.next;
            }
            if(flag == 1) {
                head1 = q;
            }
            p.next = null;

            q.next = p;

            flag++;
        }
        if(flag == 1) {
            return head;
        } else {
            return head1;
        }
    }

    public static ListNode re2(ListNode head) {
        ListNode head1 = null;
        ListNode temList = head;

        if(temList != null) {
            ListNode p = new ListNode(-1);
            p.data = temList.data;
            p.next = null;
            head1 = p;
            temList = temList.next;
        }
        while(temList != null) {
            ListNode q = new ListNode(-1);
            q.data = temList.data;
            q.next = head1;
            head1 = q;
            temList = temList.next;
        }
        return head1;
    }

    public static ListNode re3(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static void print(ListNode head) {
        while(head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println("");
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {
        ListNode head11 = new ListNode(-1);
        ListNode p = new ListNode(-1);
        for(int i = 0; i < 10; i++) {
            ListNode newNode = new ListNode(-1);
            newNode.data = i;
            if(i == 0) {
                head11 = newNode;
                p = newNode;
                p.next = null;
            }
            p.next = newNode;
            p = p.next;
            p.next = null;

        }
        print(head11);

//        print(re2(head11));

//        print(re1(head11));
//        print(re2(head11));
        print(re3(head11));


    }
}
