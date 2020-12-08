package com.lr.flink.flink.beaver;

/**
 * @author chengtao
 * @date 21/11/2020 - 23:31
 */
public interface ScrollQuery {

    boolean hasNext();

    Row next();

    void close();

    ScrollQuery EMPTY = new ScrollQuery() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Row next() {
            return null;
        }

        @Override
        public void close() {

        }
    };
}
