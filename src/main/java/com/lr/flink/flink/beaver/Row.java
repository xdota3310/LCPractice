package com.lr.flink.flink.beaver;

import java.util.Map;

public interface Row extends Fields {
    boolean containsField(String field);
    Object getField(String field);
    void setField(String field, Object value);
    void addFields(Map<String, Object> map);
    Object remove(String field);
    Row clone();
    Map<String, Object> asMap();
    void clear();
}

