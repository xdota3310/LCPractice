package com.lr.leetcode.queue;

/**
 * 设计实现双端队列。
 *
 * @author shijie.xu
 * @since 2019年10月12日
 */
public class MyCircularDeque {

    int[] arr;

    int index;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        arr = new int[k];
        index = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(index < arr.length) {
            for(int i = index; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = value;
            index += 1;
            return true;
        }
        return false;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(index < arr.length) {
            arr[index] = value;
            index += 1;
            return true;
        }
        return false;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(index > 0) {
            for(int i = 0; i < index; i++) {
                arr[i] = arr[i++];
            }
            index -= 1;
            return true;
        }
        return false;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(index > 0) {
            index -= 1;
            return true;
        }
        return false;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(index > 0) {
            return arr[0];
        }
        return -1;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(index > 0) {
            return arr[index - 1];
        }
        return -1;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return index <= 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return index >= arr.length;
    }
}
