package com.company.P2018_11_23;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月03日
 */
public class Sort2 {
    public static void quickSortAsc(int[] a, int pre, int tail) {
        if(pre >= tail) {
            return;
        }
        int val = a[(pre + tail) >>> 1];
        int i = pre;
        int j = tail;
        while(i <= j) {
            while(a[i] < val) {
                i++;
            }
            while(a[j] > val) {
                j--;
            }
            if(i < j) {
                swap(a, i, j);
            } else {
                i++;
            }
        }
        quickSortAsc(a, pre, j);
        quickSortAsc(a, i, tail);
    }

    public static void mergeSort(int[] a, int[] res, int pre, int tail) {
        if(pre >= tail) {
            return;
        }
        int mid = (pre + tail) >>> 1;
        int pre1 = pre, tail1 = mid;
        int pre2 = mid + 1, tail2 = tail;
        mergeSort(a, res, pre1, tail1);
        mergeSort(a, res, pre2, tail2);
        int index = pre;
        while(pre1 <= tail1 && pre2 <= tail2) {
            res[index++] = a[pre1] < a[pre2] ? a[pre1++] : a[pre2++];
        }
        while(pre1 <= tail1) {
            res[index++] = a[pre1++];
        }
        while(pre2 <= tail2) {
            res[index++] = a[pre2++];
        }
        while(pre <= tail) {
            a[pre] = res[pre];
            pre++;
        }
    }

    public static void heapSort(int[] arr) {
        int length = arr.length;
        for(int i = (length >> 1) - 1; i >= 0; i--) {
            adjust(arr,i,length);
        }
        for(int i= length - 1;i>0;i--){
            swap(arr,i,0);
            adjust(arr,0,i);
        }
    }

    private static void adjust(int[] a, int start, int end) {
        int root = a[start];
        int child;
        while(leftChild(start)<end){
            child = leftChild(start);
            int rightChild = child +1;
            if(rightChild<end && a[child]<a[rightChild]){
                child++;
            }
            if(root < a[child]){
                a[start] = a[child];
            }else {
                break;
            }
            start = child;
        }
        a[start] = root;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 3, 7, 6, 2, 4};
        int[] c = {1, 5, 3, 7, 6, 2, 4};
        int[] b = {1, 5, 3, 7, 6, 2, 4};
        int[] r = new int[b.length];
        quickSortAsc(a, 0, a.length-1);
        mergeSort(b, r, 0, b.length-1);
        heapSort(c);

        System.out.println();
    }
}
