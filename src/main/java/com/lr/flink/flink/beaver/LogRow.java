package com.lr.flink.flink.beaver;

import java.util.HashMap;
import java.util.Map;

public class LogRow extends HashMap<String, Object> implements Row {
    public static final long serialVersionUID = 1L;

    @Override
    public boolean containsField(String field) {
        return this.containsKey(field);
    }

    @Override
    public void setField(String key, Object value) {
        this.put(key, value);
    }

    @Override
    public Object getField(String key) {
        return this.get(key);
    }

    @Override
    public Object remove(String field) {
        return super.remove(field);
    }

    @Override
    public Map<String, Object> asMap() {
        return this;
    }

    @Override
    public void addFields(Map<String, Object> map) {
        this.putAll(map);
    }

    @Override
    public LogRow clone() {
        LogRow row = new LogRow();
        row.putAll(this);
        return row;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
