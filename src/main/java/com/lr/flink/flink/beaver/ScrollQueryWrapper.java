package com.lr.flink.flink.beaver;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author chengtao
 * @date 22/11/2020 - 13:27
 */
public class ScrollQueryWrapper implements ScrollQuery {
    private BeaverScrollQuery scrollQuery;
    private Iterator<LogRow> rowIterator;
    public ScrollQueryWrapper(BeaverScrollQuery scrollQuery) {
        this.scrollQuery = scrollQuery;
    }

    @Override
    public boolean hasNext() {
        if (rowIterator == null || !rowIterator.hasNext()) {
            if (scrollQuery.hasNext()) {
                rowIterator = scrollQuery.next().iterator();
            }
        }
        return rowIterator != null && rowIterator.hasNext();
    }

    @Override
    public LogRow next() {
        if (rowIterator == null || !rowIterator.hasNext()) {
            throw new NoSuchElementException();
        }
        LogRow row = rowIterator.next();
        return row;
    }

    @Override
    public void close() {
        if (scrollQuery != null) {
            scrollQuery.close();
            scrollQuery = null;
        }
    }
}
