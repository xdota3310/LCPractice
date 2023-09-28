package com.lr.zuochengyun_algo;
public class K个一组反转链表 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tempHead = new ListNode();
        tempHead.next = head;
        ListNode subHead = tempHead;
        ListNode subTail = tempHead;
        subTail = moveK(subTail, k);
        do {
            ListNode pre = subHead;
            ListNode nextHead = subTail.next;
            pre.next = reverse(subHead.next, subTail);

            ListNode p = pre;
            while (p.next != null) {
                p = p.next;
            }
            p.next = nextHead;
            subHead = p;
            subTail = moveK(subHead, k);
        } while (subTail != null);
        return tempHead.next;
    }

    public ListNode reverse(ListNode head, ListNode tail) {
        ListNode newHead = null;
        while (head != tail) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        tail.next = newHead;
        newHead = head;
        return newHead;
    }

    public ListNode moveK(ListNode node, int k) {
        ListNode p = node;
        for (int i = 0; i < k; i++) {
            if (p != null) {
                p = p.next;
            }
        }
        return p;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode tail = head;
        for (int i = 1; i < 6; i++) {
            tail.next = new ListNode(i);
            tail = tail.next;
        }


        K个一组反转链表 k = new K个一组反转链表();
        k.reverseKGroup(head.next, 2);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
