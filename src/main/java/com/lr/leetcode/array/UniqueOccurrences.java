package com.lr.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xu.shijie
 * @since 2020/10/28
 */
public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Integer n = null;
        for (int i = 0; i < arr.length; ) {
            if (n != null) {
                if (n <= i) {
                    arr[n]++;
                    n = null;
                    i++;
                } else {
                    int temp = n;
                    n = arr[temp];
                    arr[temp] = 1;
                }
            } else {
                n = arr[arr[i]];
                arr[arr[i]] = 1;
            }
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : arr){
            if(map.containsKey(num)){
                map.put(num,map.get(num) + 1);
            }else {
                map.put(num,1);
            }
        }
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            entry.getValue();
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 2, 1, 1, 3};
        new UniqueOccurrences().uniqueOccurrences(a);
        System.out.println();
    }
}
