package com.lr.leetcode.linkedList;

import com.lr.leetcode.base.ListNode;
import com.lr.leetcode.base.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 *
 * @author shijie.xu
 * @since 2019年09月06日
 */
public class SortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        return doGenerate(head, null);
    }

    public TreeNode doGenerate(ListNode head, ListNode tail) {
        ListNode mid = findMidNode(head, tail);
        if (mid == null) {
            return null;
        }
        TreeNode treeNode = new TreeNode(mid.val);
        treeNode.left = doGenerate(head, mid);
        treeNode.right = doGenerate(mid.next, tail);
        return treeNode;
    }

    public ListNode findMidNode(ListNode head, ListNode tail) {
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode != tail && fastNode.next != tail) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }
        if (head == tail) {
            return null;
        }
        return slowNode;
    }
}
