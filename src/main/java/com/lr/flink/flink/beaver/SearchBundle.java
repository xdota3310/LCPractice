package com.lr.flink.flink.beaver;

import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.Set;

/**
 * @author chengtao
 * @date 20/11/2020 - 20:17
 */
public class SearchBundle implements Serializable {
    private Set<String> fields;
    private String domain;
    private String app;
    private long earliest;
    private long latest;
    private String indexPrefix;
    private String queryString;

    public SearchBundle setFields(Set<String> fields) {
        this.fields = fields;
        return this;
    }

    public String getQueryString() {
        return queryString;
    }

    public SearchBundle setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public Set<String> getFields() {
        return fields;
    }

    public SearchBundle addField(String field) {
        if (fields == null) {
            fields = Sets.newHashSet(field);
        } else {
            fields.add(field);
        }
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public SearchBundle setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getApp() {
        return app;
    }

    public SearchBundle setApp(String app) {
        this.app = app;
        return this;
    }

    public long getEarliest() {
        return earliest;
    }

    public SearchBundle setEarliest(long earliest) {
        this.earliest = earliest;
        return this;
    }

    public long getLatest() {
        return latest;
    }

    public SearchBundle setLatest(long latest) {
        this.latest = latest;
        return this;
    }

    public String getIndexPrefix() {
        return indexPrefix;
    }

    public SearchBundle setIndexPrefix(String indexPrefix) {
        this.indexPrefix = indexPrefix;
        return this;
    }

}
