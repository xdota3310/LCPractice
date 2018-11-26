package com.company.P2018_11_21;

import java.util.Date;

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

    public static ListNode re1(ListNode list) {
        int flag = 1;
        ListNode p;
        ListNode q;
        ListNode head = new ListNode();
        ListNode temList = list;

        while(temList.next != null) {
            p = temList;
            q = temList.next;
            while(q.next != null) {
                p = q;
                q = q.next;
            }
            if(flag == 1) {
                head.next = q;
            }
            p.next = null;
            if(p != temList) {
                q.next = p;
            } else {
                return head;
            }
            flag++;
        }
        return head;
    }

    public static ListNode re2(ListNode list) {
        ListNode head = null;
        ListNode temList = list;

        if(temList.next != null) {
            ListNode p = new ListNode();
            p.data = temList.next.data;
            p.next = null;
            head = p;
            temList = temList.next;
        }
        while(temList.next != null) {
            ListNode q = new ListNode();
            q.data = temList.next.data;
            q.next = head;
            head = q;
            temList = temList.next;
        }
        ListNode headNode = new ListNode();
        headNode.next = head;
        head = headNode;
        return head;
    }

    public static void print(ListNode head) {
        while(head != null && head.next != null) {
            System.out.print(head.next.data+" ");
            head = head.next;
        }
        System.out.println("");
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {
        ListNode head11 = new ListNode();
        ListNode p = head11;
        for(int i = 0; i < 10; i++) {
            ListNode newNode = new ListNode();
            newNode.data = i;
            p.next = newNode;
            p = p.next;
            p.next = null;
        }
        print(head11);

        print(re2(head11));
        print(re1(head11));
//        print(re2(head11));


    }
}
