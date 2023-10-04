package com.lr.zuochengyun_algo;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//lc-895
public class FreqStack {
    Map<Integer, Integer> valToFreq = new HashMap<>();
    Map<Integer, ListNode> levelToNode = new HashMap<>();
    Stack<ListNode> stack = new Stack<>();
    int maxFreq = 0;

    public FreqStack() {

    }

    public void push(int val) {
        Integer freq = valToFreq.get(val);
        if (freq == null) {
            freq = 1;
        } else {
            freq += 1;
        }
        valToFreq.put(val, freq);
        // 小于等于max
        if (freq <= maxFreq) {
            ListNode head = levelToNode.get(freq);
            ListNode p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new ListNode(val);
        }
        // 大于max
        else {
            maxFreq = freq;
            ListNode node = new ListNode(val);
            levelToNode.put(freq, node);
            stack.push(node);
        }
    }

    public int pop() {
        ListNode headNode = levelToNode.get(maxFreq);
        ListNode p = headNode;
        while (p.next != null && p.next.next != null) {
            p = p.next;
        }
        if (p.next == null) {
            int val = p.val;
            levelToNode.remove(maxFreq);
            valToFreq.put(val, maxFreq - 1);
            stack.pop();
            maxFreq -= 1;
            return val;
        } else {
            ListNode next = p.next;
            p.next = null;
            int val = next.val;
            int freq = valToFreq.get(val);
            if (freq == 1) {
                valToFreq.remove(val);
            } else {
                valToFreq.put(val, freq - 1);
            }
            return val;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
