package com.company.拓扑排序;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * @author shijie.xu
 * @since 2019年04月09日
 */
public class CourseSchedule {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // 先处理极端情况
        if (numCourses <= 0) {
            return new int[0];
        }
        // 邻接表表示3 = {HashSet@532}  size = 0
        HashSet<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new HashSet<>();
        }
        // 入度表
        int[] inDegree = new int[numCourses];
        // 遍历 prerequisites 的时候，把 邻接表 和 入度表 都填上
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
            inDegree[p[0]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.addLast(i);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 当前入度为 0 的结点
            Integer inDegreeNode = queue.removeFirst();
            // 加入结果集中
            res.add(inDegreeNode);
            // 下面从图中删去
            // 得到所有的后继课程，接下来把它们的入度全部减去 1
            HashSet<Integer> nextCourses = graph[inDegreeNode];
            for (Integer nextCourse : nextCourses) {
                inDegree[nextCourse]--;
                // 马上检测该结点的入度是否为 0，如果为 0，马上加入队列
                if (inDegree[nextCourse] == 0) {
                    queue.addLast(nextCourse);
                }
            }
        }
        // 如果结果集中的数量不等于结点的数量，就不能完成课程任务，这一点是拓扑排序的结论
        int resLen = res.size();
        if (resLen == numCourses) {
            int[] ret = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                ret[i] = res.get(i);
            }
            return ret;
        } else {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        int[ ][ ] intArray={{1,0},{2,0},{3,1},{3,2}};
        System.out.println(findOrder(4,intArray));
    }
}
