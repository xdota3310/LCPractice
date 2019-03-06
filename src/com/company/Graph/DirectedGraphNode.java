package com.company.Graph;

import java.util.ArrayList;

/**
 * 有向图
 *
 * @author shijie.xu
 * @since 2019年01月23日
 */
public class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}
