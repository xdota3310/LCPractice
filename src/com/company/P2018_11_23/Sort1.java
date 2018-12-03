package com.company.P2018_11_23;

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

    //    stable
    public static void dubble(int a[]) {
        int n = a.length;
        int temp = -1;
        if(n <= 0) {
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
    }

    public static void select(int a[]) {

    }

    public static int seleMin(int a[]) {
        int min = 999999999;
        int index = -1;
        int n = a.length;
        for(int i = 0; i < n; i++) {
            if(a[i] < min) {
                min = a[i];
                index = i;
            }
        }
        for(int i = index; i <n-index;i++){
            a[i]=a[i+1];

        }
            return min;
    }

    public static void main(String[] args) {
        int a[] = {4, 6, 2, 53, 43, 2, 354, 34637, 1, 2, 34, 436, 567, 8, 8, 789};
        dubble(a);
        seleMin(a);
        System.out.println(a.toString());
    }

}
