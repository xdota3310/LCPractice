package com.company.P2018_11_23;

import com.sun.istack.internal.NotNull;

import java.util.Random;

/**
 * 我们讲过，特定算法是依赖特定的数据结构的。我们今天讲的几种排序算法，都是基于数组实现
 * 的。如果数据存储在链表中，这三种排序算法还能工作吗？如果能，那相应的时间、空间复杂度又
 * 是多少呢？
 * 冒、选（希）、选
 *
 * @author shijie.xu
 * @since 2018年11月23日
 */
public class Sort1 {

    /**
     * 冒泡排序
     * 原地、稳定
     * 时间O(n^2) 空间O(1)
     *
     * @param a
     */
    public static void dubble(int a[]) {
        Long start = System.currentTimeMillis();
        int n = a.length;
        int temp = -1;
        if(n <= 1) {
            return;
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n - 1 - i; j++) {
                if(a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("{dubble:" + (end - start) + "}");
        print(a);
    }

    public static void insertionSort(int[] a) {
        Long start = System.currentTimeMillis();
        int n = a.length;
        if(n <= 1) {
            return;
        }
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(a[i] < a[j]) {
                    move(a, i, j);
                }
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("{insertionSort:" + (end - start) + "}");
        print(a);
    }

    /**
     * @param b
     * @param index  数组中需要移动的元素索引
     * @param target 要移动的位置
     */
    private static void move(int[] b, int index, int target) {
        int temp = b[index];
        for(int i = index - 1; i >= target; i--) {
            b[i + 1] = b[i];
        }
        b[target] = temp;
    }

    /**
     * 插入排序
     * 稳定、原地
     * 时间O(n^2)、空间O(1)
     *
     * @param a
     */
    public static void insertionSort2(int[] a) {
        Long start = System.currentTimeMillis();
        int n = a.length;
        if(n <= 1) {
            return;
        }
        for(int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
// 查找插入的位置
            for(; j >= 0; --j) {
                if(a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
        Long end = System.currentTimeMillis();
        System.out.println("{insertionSort2:" + (end - start) + "}");
        print(a);
    }

    /**
     * 希尔排序
     * 不稳定、原地
     * 时间平均O(n(logn)^2) 空间O(1)
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        Long start = System.currentTimeMillis();
        int length = array.length;
        if(length <= 1) {
            return;
        }
        int num = length >> 1;
        while(num >= 1) {
            for(int i = num; i < length; i++) {
                int j = i - num;
                int temp = array[i];
                while(j >= 0 && temp < array[j]) {
                    array[j + num] = array[j];
                    j = j - num;
                }
                array[j + num] = temp;
            }
            num = num >> 1;
        }
        Long end = System.currentTimeMillis();
        System.out.println("{shellSort:" + (end - start) + "}");
        print(array);
    }

    public static void selectSort(int[] a) {
        int min = 999999999;
        int index = -1;
        int n = a.length;
        for(int i = 0; i < n; i++) {
            if(a[i] < min) {
                min = a[i];
                index = i;
            }
        }
        for(int i = index; i < n - index; i++) {
            a[i] = a[i + 1];

        }
    }

    /**
     * 快速排序
     * 时间O(nlogn) 空间O(1)
     *
     * @param a
     */
    public static void quickSort(int[] a) {
        Long start = System.currentTimeMillis();
        int length = a.length;
//        qSort(a, 0, length - 1);
        recursiveQuickSort(a, 0, length - 1);
        Long end = System.currentTimeMillis();
        System.out.println("{quickSort:" + (end - start) + "}");
        print(a);
    }

    private static void recursiveQuickSort(int[] a, int p, int r) {
        if(p >= r) {
            return;
        }
        int pivot = a[(p + r) >> 1];
        int i = p, j = r;
        while(i <= j) {
            while(a[i] < pivot) {
                ++i;
            }
            while(a[j] > pivot) {
                --j;
            }
            if(i < j) {
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
                ++i;
                --j;
            } else if(i == j) {
                ++i;
            }
        }

        recursiveQuickSort(a, p, j);
        recursiveQuickSort(a, i, r);


    }


    private static void qSort(int[] arr, int head, int tail) {
        if(head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while(i <= j) {
            while(arr[i] < pivot) {
                ++i;
            }
            while(arr[j] > pivot) {
                --j;
            }
            if(i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                ++i;
                --j;
            } else if(i == j) {
                ++i;
            }
        }
        qSort(arr, head, j);
        qSort(arr, i, tail);
    }

    /**
     * 归并排序
     * 稳、非原地
     * 时间O(nlogn)、空间O(n)
     *
     * @param a
     */
    public static void mergeSort(@NotNull int[] a) {
        Long start = System.currentTimeMillis();
        int length = a.length;
        int[] res = new int[length];
        recursiveMergeSort(a, res, 0, length - 1);
        Long end = System.currentTimeMillis();
        System.out.println("{mergeSort:" + (end - start) + "}");
        print(a);

    }

    private static void recursiveMergeSort(int[] a, int[] res, int start, int end) {
        if(start >= end) {
            return;
        }
        int mid = ((end - start) >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        recursiveMergeSort(a, res, start1, end1);
        recursiveMergeSort(a, res, start2, end2);
        int k = start;
        while(start1 <= end1 && start2 <= end2) {
            res[k++] = a[start1] <= a[start2] ? a[start1++] : a[start2++];
        }
        while(start1 <= end1) {
            res[k++] = a[start1++];
        }
        while(start2 <= end2) {
            res[k++] = a[start2++];
        }
        for(int i = start; i < k; i++) {
            a[i] = res[i];
        }

    }


    private static void print(int[] t) {
//        for(int tt : t) {
//            System.out.print(tt + " ");
//        }
    }

    private static int[] getArray(int size) {
        int[] table = new int[size];
        Random df = new Random();
        for(int t = 0; t < size; t++) {
            table[t] = df.nextInt(size);
        }
        return table;
    }

    public static void main(String[] args) {
//        int a[] = {4, 6, 2, 53, 43, 2, 354, 34637, 1, 2, 34, 436, 567, 8, 8, 789};
        int[] t = getArray(100000);
        int[] a = new int[100000];
        int[] b = new int[100000];
        int[] c = new int[100000];
        int[] d = new int[100000];
        int[] e = new int[100000];
        int[] f = new int[100000];
        System.arraycopy(t, 0, a, 0, t.length);
        System.arraycopy(t, 0, b, 0, t.length);
        System.arraycopy(t, 0, c, 0, t.length);
        System.arraycopy(t, 0, d, 0, t.length);
        System.arraycopy(t, 0, e, 0, t.length);
        System.arraycopy(t, 0, f, 0, t.length);

        dubble(a);
        insertionSort(b);
        insertionSort2(c);
        shellSort(d);
        mergeSort(e);
        quickSort(f);
    }

}
