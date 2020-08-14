//package com.baidubaikespider.crawler;
//
//import java.util.concurrent.Callable;
//
///**
// * 包装了爬虫任务，用以提交线程池
// */
//
//public class Crawler implements Callable<Result> {
//
//    /**
//     * 爬虫ID，成功爬一个就自增1，所以也可以认为是已爬个数
//     */
//    public static int CrawlerID = 0;
//    /**
//     * 该爬虫要爬的Url
//     */
//    private String url = null;
//
//    public Crawler(String url) {
//        this.url = url;
//    }
//
//    @Override
//    public Result call() throws Exception {
//        HtmlParserTool parserTool = new HtmlParserTool();
//        //通过HtmlParserTool获取结果
//        Result result = parserTool.getResult(url, CrawlerStart.ifUseProxy);
//        CrawlerID++;
//        return result;
//    }
//
//    public String getLinks() {
//        return url;
//    }
//
//    public void setLinks(String links) {
//        this.url = links;
//    }
//}