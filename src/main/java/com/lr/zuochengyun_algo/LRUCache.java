import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class LRUCache {
    Map<Integer, ListNode> kToV = new HashMap<>();
    ListNode head = new ListNode();
    ListNode tail = head;
    int maxSize = 0;
    int currentSize = 0;

    public LRUCache(int capacity) {
        maxSize = capacity;
    }

    public int get(int key) {
        if (kToV.containsKey(key)) {
            ListNode node = kToV.get(key);
            ListNode pre = node.pre;
            ListNode next = node.next;
            if (next == null) {
                return node.val;
            }
            if (pre != null) {
                pre.next = next;
            }
            next.pre = pre;
            node.next = null;
            node.pre = tail;
            tail.next = node;
            tail = tail.next;
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (maxSize <= 0) {
            return;
        }
        ListNode node = new ListNode();
        node.val = value;
        node.key = key;
        if (kToV.containsKey(key)) {
            ListNode oldNode = kToV.get(key);
            ListNode oldPre = oldNode.pre;
            ListNode oldNext = oldNode.next;
            oldPre.next = oldNext;
            if (oldNext != null) {
                oldNext.pre = oldPre;
            } else {
                tail = oldPre;
            }
            oldNode.pre = null;
            oldNode.next = null;
        } else {
            if (currentSize == maxSize) {
                ListNode removed = head.next;
                ListNode next = removed.next;
                if (next == null) {
                    head.next = null;
                    tail = head;
                } else {
                    next.pre = head;
                    head.next = next;
                }
                kToV.remove(removed.key);
            } else {
                currentSize++;
            }
        }
        kToV.put(key, node);
        tail.next = node;
        node.pre = tail;
        tail = tail.next;
    }

    public static class ListNode {
        int key;
        int val;
        ListNode pre;
        ListNode next;
    }

    public static void main(String[] args) {
//        LRUCache lruCache = new LRUCache(1);
//        lruCache.get(6);
//        lruCache.get(8);
//        lruCache.put(12, 1);
//        lruCache.get(2);
//        lruCache.put(15, 11);
//        lruCache.put(5, 2);
//        lruCache.put(1, 15);
//        lruCache.put(4, 2);
//        lruCache.get(5);
//        lruCache.put(15, 115);
//        System.out.println(lruCache.get(1));
//        System.out.println(lruCache.get(2));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(11);
        list.add(12);
        System.out.println(list.get(0));
        System.out.println("-----");
        Integer removed = list.remove(0);
        System.out.println(removed);
        System.out.println("-----");
        System.out.println(list.get(0));
    }
}
