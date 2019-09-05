package com.leetcode.linkedList;

import com.leetcode.base.ListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shijie.xu
 * @since 2019年09月05日
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode atob = headA;
        ListNode btoa = headB;
        ListNode aLast = null;
        ListNode bLast = null;
        if(headA == null || headB == null) {
            return null;
        }
        while(atob != btoa) {
            if(atob.next == null) {
                aLast = atob;
                atob = headB;
                if(bLast != null && bLast != aLast) {
                    return null;
                }
            }else {
                atob = atob.next;
            }
            if(btoa.next == null) {
                bLast = btoa;
                btoa = headA;
                if(aLast != null && bLast != aLast) {
                    return null;
                }
            }else {
                btoa = btoa.next;
            }

        }
        return atob;
    }
}
