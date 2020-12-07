package com.company.Graph;

import java.util.ArrayList;

/**
 * 无向图
 *
 * @author shijie.xu
 * @since 2019年01月23日
 */
public class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        this.label = x;
        this.neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
