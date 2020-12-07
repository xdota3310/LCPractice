package com.baidubaikespider.crawler;

import java.util.ArrayList;

/**
 */

//该类为结果类，爬取结果存放入此类中
public class Result {
    /**
     * 标题
     */
    private String title;
    /**
     * 链接
     */
    private String url;
    /**
     * 概要
     */
    private String summary;
    /**
     * 基本信息
     */
    private String basicInfo;
    /**
     * 目录
     */
    private String index;
    /**
     * 具体内容
     */
    private String context;
    /**
     * 参考文献（含链接）
     */
    private String reference;
    /**
     * 标签
     */
    private String tags;
    //
    /**
     * 数据统计（爬取的时间、多少次编辑、上次更新时间）
     */
    private String statics;

    private ArrayList<String> urlLink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public ArrayList<String> getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(ArrayList<String> urlLink) {
        this.urlLink = urlLink;
    }

    @Override
    public String toString() {
        return "Title:" + title + "\nContext:\n" + context + "\nUrlLink:" + urlLink + "\n";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStatics() {
        return statics;
    }

    public void setStatics(String statics) {
        this.statics = statics;
    }
}
