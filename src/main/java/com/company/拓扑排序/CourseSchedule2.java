package com.company.拓扑排序;

import java.util.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年04月09日
 */
public class CourseSchedule2 {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0) {
            return new int[0];
        }
        HashSet<Integer>[] table = new HashSet[numCourses];
        for(int i = 0; i < numCourses; i++) {
            table[i] = new HashSet<>();
        }
        int[] inNum = new int[numCourses];
        for(int[] p : prerequisites) {
            table[p[1]].add(p[0]);
            inNum[p[0]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            if(inNum[i] == 0) {
                queue.addLast(i);
            }
        }
        while(!queue.isEmpty()) {
            int num = queue.removeFirst();
            res.add(num);
            Iterator<Integer> iterator = table[num].iterator();
            while(iterator.hasNext()) {
                int temp = iterator.next();
                inNum[temp]--;
                if(inNum[temp] == 0) {
                    queue.add(temp);
                }
            }
        }
        if(res.size() == numCourses) {
            int [] re = new int[numCourses];
            for(int i=0;i<res.size();i++) {
                re[i]=res.get(i);
            }
            return re;
        }else {
            return new int[0];
        }
    }

    public static void main(String[] args) {
//        {1, 0}, {2, 0}, {3, 1}, {3, 2}
        int[][] intArray = {};
        findOrder(2, intArray);
    }
}
