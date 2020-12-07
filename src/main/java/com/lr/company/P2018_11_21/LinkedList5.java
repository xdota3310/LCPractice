package com.company.P2018_11_21;

import java.util.*;

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
            q = q.next.next;
            p = p.next;
        }
        if(q.next == null) {
            p.next = null;
            return p;
        } else if(q.next.next == null) {
            p = p.next;
            p.next = null;
            return p;
        }
        return head;
    }

    public static void main(String[] args) {
//        List<String> names = Arrays.asList(" 张三 ", " 李四 ", " 王老五 ", " 李三 ", " 刘老四 ", " 王小二 ", " 张四 ", " 张五六七 ");
//
//        String maxLenStartWithZ = names.stream()
//        .filter(name -> name.startsWith(" 张 "))
//        .mapToInt(String::length)
//        .max()
//        .toString();
//
//        System.out.println(maxLenStartWithZ);
        HashMap<String,String> hashMap = new HashMap<>(12);
        hashMap.put("x","xu");
        hashMap.put("s","shi");
        hashMap.put("jj","jie");

    }


}
