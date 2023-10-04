package com.lr.zuochengyun_algo;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> left = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    PriorityQueue<Integer> right = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {

    }

    public void addNum(int num) {
        int leftSize = left.size();
        int rightSize = right.size();

        Integer leftMax = left.peek();
        Integer rightMin = right.peek();
        if (leftSize - rightSize >= 1) {
            // 放在右边
            if (rightMin == null && num > leftMax) {
                right.offer(num);
            } else if (num <= leftMax) {
                left.poll();
                left.offer(num);
                right.offer(leftMax);
            } else if (num >= rightMin) {
                right.offer(num);
            } else if (num < rightMin && num > leftMax) {
                right.offer(num);
            }
        } else {
            // 放在左边
            if (leftMax == null || num <= leftMax) {
                left.offer(num);
            } else if (num > leftMax && num < rightMin){
                left.offer(num);
            } else if (num >= rightMin) {
                right.poll();
                right.offer(num);
                left.offer(rightMin);
            }
        }
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            return (((double) left.peek() - (double) right.peek()) / 2) + (double) right.peek();
        } else {
            return (double) left.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian());
    }
}
