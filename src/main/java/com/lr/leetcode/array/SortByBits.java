package com.lr.leetcode.array;

/**
 * 1356. 根据数字二进制下 1 的数目排序
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * <p>
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * <p>
 * 请你返回排序后的数组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [0,1,2,3,4,5,6,7,8]
 * 输出：[0,1,2,4,8,3,5,6,7]
 * 解释：[0] 是唯一一个有 0 个 1 的数。
 * [1,2,4,8] 都有 1 个 1 。
 * [3,5,6] 有 2 个 1 。
 * [7] 有 3 个 1 。
 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 * 示例 2：
 * <p>
 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 * 示例 3：
 * <p>
 * 输入：arr = [10000,10000]
 * 输出：[10000,10000]
 * 示例 4：
 * <p>
 * 输入：arr = [2,3,5,7,11,13,17,19]
 * 输出：[2,3,5,17,7,11,13,19]
 * 示例 5：
 * <p>
 * 输入：arr = [10,100,1000,10000]
 * 输出：[10,100,10000,1000]
 *
 * @author xu.shijie
 * @since 2020/11/6
 */
public class SortByBits {
    public int[] sortByBits(int[] arr) {
//        quickSort(arr, 0, arr.length - 1);

//        int[] k = new int[arr.length];
//        mergeSort(arr, k, 0, arr.length - 1);

        heapSort(arr);
        return arr;
    }

    private void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int midVal = arr[(start + end) >>> 1];
        int pre = start, tail = end;
        while (pre <= tail) {
            while (compareLT(arr[pre], midVal)) {
                pre++;
            }
            while (compareGT(arr[tail], midVal)) {
                tail--;
            }
            if (pre < tail) {
                int temp = arr[pre];
                arr[pre] = arr[tail];
                arr[tail] = temp;
                pre++;
                tail--;
            } else if (pre == tail) {
                pre++;
            }
        }
        quickSort(arr, start, tail);
        quickSort(arr, pre, end);
    }

    private void mergeSort(int[] arr, int[] k, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >>> 1;
        int kIndex = start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        mergeSort(arr, k, start1, end1);
        mergeSort(arr, k, start2, end2);
        while (start1 <= end1 && start2 <= end2) {
            k[kIndex++] = !compareGT(arr[start1], arr[start2]) ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            k[kIndex++] = arr[start1++];
        }
        while (start2 <= end2) {
            k[kIndex++] = arr[start2++];
        }
        while (start <= end) {
            arr[start] = k[start];
            start++;
        }
    }

    private void heapSort(int[] arr) {
        for (int i = arr.length >> 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeap(arr, 0, i);
        }
    }

    private void adjustHeap(int[] arr, int start, int end) {
        int child;
        int val = arr[start];
        for (; getLeft(start) < end; start = child) {
            child = getLeft(start);
            if (child < end - 1 && compareLT(arr[child], arr[child + 1])) {
                child++;
            }
            if (compareLT(val, arr[child])) {
                arr[start] = arr[child];
            } else {
                break;
            }
        }
        arr[start] = val;
    }

    private int getLeft(int i) {
        return i * 2 + 1;
    }

    private boolean compareGT(int num1, int num2) {
        int bitCount1 = getBitCount(num1);
        int bitCount2 = getBitCount(num2);
        return bitCount1 == bitCount2 ? num1 > num2 : bitCount1 > bitCount2;
    }

    private boolean compareLT(int num1, int num2) {
        int bitCount1 = getBitCount(num1);
        int bitCount2 = getBitCount(num2);
        return bitCount1 == bitCount2 ? num1 < num2 : bitCount1 < bitCount2;
    }

    private int getBitCount(int num) {
        int bitCount = 0;
        for (int i = 1; i > 0; i = i << 1) {
            if ((num & i) == i) {
                bitCount++;
            }
        }
        return bitCount;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        new SortByBits().sortByBits(arr);
        System.out.println(1);
    }
}
