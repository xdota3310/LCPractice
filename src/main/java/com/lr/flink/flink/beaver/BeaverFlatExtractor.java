package com.lr.flink.flink.beaver;

import cn.yottabyte.beaver.action.search.SearchResponse;
import cn.yottabyte.beaver.search.DvIterator;
import cn.yottabyte.beaver.search.SearchHit;
import cn.yottabyte.beaver.search.SearchHitField;
import cn.yottabyte.beaver.search.SearchHits;

import java.util.*;

public class BeaverFlatExtractor {
    private Set<String> remainRootFieldSet;

    public static final Set<String> ignoreFields;
    static {
        ignoreFields = new HashSet<>();
    }

    public BeaverFlatExtractor() {
    }

    public BeaverFlatExtractor(Set<String> remainRootFieldSet) {
        this.remainRootFieldSet = remainRootFieldSet;
    }

    public List<LogRow> extract(SearchResponse response) {
        return this.extract(response.getHits());
    }

    public List<LogRow> extract(SearchHits hits) {
        return this.extract(hits, Long.MAX_VALUE);
    }

    public List<LogRow> extract(SearchHits hits, long leftCount) {
        if (hits == null) {
            return null;
        }
        List<LogRow> splRows;
        if (hits.hasDvFields()) {
            splRows = this.extractDv(hits, leftCount);
        } else {
            splRows = this.extract(Arrays.asList(hits.getHits()), leftCount);
        }
        return splRows;
    }

    public List<LogRow> extract(Collection<SearchHit> hits, long leftCount) {
        List<LogRow> rows = new ArrayList<>(hits.size());
        long count = 0;
        for (SearchHit hit : hits) {
            LogRow row = extractHit(hit);
            if (row != null) {
                if (count++ > leftCount) {
                    break;
                }
                rows.add(row);
            }
        }
        return rows;
    }

    public List<LogRow> extractDv(SearchHits hits, long leftCount) {
        List<LogRow> rows = new ArrayList<>(hits.getHits().length);
        List<String> dvFields = hits.dvFields();
        DvIterator iterator = hits.detailedDvIterator();
        long count = 0;
        while (iterator.hasNext()) {
            if (count++ > leftCount) {
                break;
            }
            iterator.next();
            LogRow row = new LogRow();
            for (String dvField : dvFields) {
                String convertedKey = dvField;
                List<Object> value = iterator.values(dvField);
                if (value.size() == 1) {
                    row.setField(convertedKey, value.get(0));
                } else {
                    row.setField(convertedKey, value);
                }
            }
            rows.add(row);
        }
        return rows;
    }


    public LogRow extractHit(SearchHit hit) {
        LogRow row = new LogRow();
        extractFields(hit, row);
        extractScriptFields(hit, row);
        return row;
    }

    @SuppressWarnings("unchecked")
    public void extractScriptFields(SearchHit searchHit, LogRow row) {
        // NOTE: beaver里返回的都是平铺结构不用一层一层展开了
        Map<String, SearchHitField> fieldsMap = searchHit.getScriptFields();
        if (fieldsMap == null) {
            return;
        }
        for (String key : fieldsMap.keySet()) {
            SearchHitField hitField = fieldsMap.get(key);
            String convertedKey = key;
            Object value = null;
            Object hitValue = hitField.getValue();
            if (hitValue instanceof List) {
                List<Object> values = (List<Object>)hitValue;
                if (values == null || values.size() == 0){
                    value = null;
                } else if (values.size() == 1){
                    value = values.get(0);
                } else {
                    value = values;
                }
            } else {
                value = hitValue;
            }

            row.setField(convertedKey, value);
        }
    }

    public void extractFields(SearchHit searchHit, LogRow row) {
        Map<String, SearchHitField> fieldsMap = searchHit.getFields();
        if (fieldsMap == null) {
            return;
        }
        for (String key : fieldsMap.keySet()) {
            SearchHitField hitField = fieldsMap.get(key);
            String convertedKey = key;
            if (ignoreFields.contains(key)) {
                continue;
            }
            Object hitValue = hitField.getValue();
            if (hitValue instanceof List) {
                List<Object> objList = (List<Object>)hitValue;
                if (convertedKey.equals("tag")) {
                    row.setField(convertedKey, objList);
                } else if (objList.size() == 1) {
                    row.setField(convertedKey, objList.get(0));
                } else {
                    row.setField(convertedKey, objList);
                }
            } else {
                if (convertedKey.equals("tag")) {
                    row.setField(convertedKey, Arrays.asList(hitValue));
                } else {
                    row.setField(convertedKey, hitValue);
                }
            }
        }
    }

    private void flatSourceMap(Map<String, Object> inMap, LogRow row, String prefix) {
        for (String key : inMap.keySet()) {
            Object value = inMap.get(key);
            key = prefix + "." + key;
            flatSourceKey(row, key, value);
        }
    }

    @SuppressWarnings("unchecked")
    private void flatSourceKey(LogRow row, String key, Object value) {
        if (value instanceof Map) {
            flatSourceMap((Map<String, Object>)value, row, key);
            return;
        }
        if (!key.equals("tag")) {
            value = extractValue(value);
        }
        row.setField(key, value);
    }

    @SuppressWarnings("unchecked")
    private Object extractValue(Object value) {
        if (value instanceof List) {
            List<Object> valueList = (List<Object>)value;
            if (valueList.size() == 1) {
                return valueList.get(0);
            }
            return valueList;
        }
        if (value instanceof Object[]) {
            Object[] objArray = (Object[]) value;
            if (objArray.length == 1) {
                return objArray[0];
            }
            return objArray;
        }
        return value;
    }
}
