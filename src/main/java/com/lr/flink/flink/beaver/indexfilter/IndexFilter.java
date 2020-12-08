package com.lr.flink.flink.beaver.indexfilter;

import cn.yottabyte.search.IndexName;

/**
 * @author chengtao
 * @date 26/11/2020 - 15:03
 */
public interface IndexFilter {
    boolean filterIndex(IndexName indexName);
}
