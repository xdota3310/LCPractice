//package com.baidubaikespider.crawler;
//
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import com.baidubaikespider.proxypool.ProxyPool;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.*;
//import org.jsoup.select.Elements;
//
///**
// * HtmlParserTool HTML解析工具
// *
// * 该类主要进行HTML解析，根据html中的标签内容分解出我们要的标题、简介、目录等
// */
//public class HtmlParserTool {
//
//    public ArrayList<String> parseLink(String html) {
//        Document dom = Jsoup.parse(html);
//
//        //获取class为para的内容
//        Elements paras = dom.getElementsByClass("para");
//
//        StringBuffer sb = new StringBuffer();
//
//        for(Element link : paras) {
//            sb.append(link);
//        }
//
//
//        dom = Jsoup.parse(sb.toString());
//        //获取属性为href的元素
//        paras = dom.select("a[href]");
//
//        ArrayList<String> links = new ArrayList<>();
//
//        for(Element link : paras) {
//            //生成url
//            links.add("http://baike.baidu.com" + link.attr("href"));
//        }
//        return links;
//    }
//
//
//    /**
//     * 获取标题的内容
//     *
//     * @param html
//     * @return
//     */
//    public String parseTitle(String html) {
//
//        StringBuffer title = new StringBuffer();
//        Document dom = Jsoup.parse(html);
//        Elements elements = dom.select("h1");
//        for(Element element : elements) {
//            title.append(element.text());
//        }
//        return title.toString();
//    }
//
//    public String parseSummary(String html) {
//        StringBuffer summary = new StringBuffer();
//        Document dom = Jsoup.parse(html);
//        //简介
//        Elements elements = dom.getElementsByClass("lemma-summary");
//        //Elements elements = dom.getElementsByClass("lemma-summary").get(0).getElementsByClass("para");
//        for(Element element : elements) {
//            summary.append(element.text() + "\n");
//        }
//        return summary.toString();
//    }
//
//    public String parseBasicInfo(String html) {
//        StringBuffer basicInfo = new StringBuffer();
//        Document dom = Jsoup.parse(html);
//        //基本信息
//        Elements elements = dom.getElementsByClass("basicInfo-item");
//        boolean isName = true;
//        for(Element element : elements) {
//            if(isName) {
//                basicInfo.append(element.text() + "  ");
//            } else {
//                basicInfo.append(element.text() + "\n");
//            }
//
//            isName = !isName;
//        }
//        return basicInfo.toString();
//    }
//
//    public String parseIndex(String html) {
//        StringBuffer index = new StringBuffer();
//        Document dom = Jsoup.parse(html);
//        //目录
//        Elements elements = dom.getElementsByClass("lemma-catalog");
//        for(Element element : elements) {
//            index.append(element.text() + "\n");
//        }
//        return index.toString();
//    }
//
//    public String parseContext(String html) {
//        StringBuffer context = new StringBuffer();
//        Document dom = Jsoup.parse(html);
//        //具体内容
//        Elements elements = dom.getElementsByClass("para");
//        for(Element element : elements) {
//            context.append(element.text() + "\n");
//        }
//
//        return context.toString();
//    }
//
//    /**
//     * 词条统计
//     * @param html
//     * @return
//     */
//    public String parseStatistics(String html) {
//        StringBuffer Statistics = new StringBuffer();
//        Document dom = Jsoup.parse(html);
//        Elements elements = dom.getElementsByClass("lemma-statistics");
//        for(Element element : elements) {
//            Statistics.append(element.text() + "\n");
//        }
//        //设置日期格式
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        df.format(new Date());
//        return df.format(new Date()) + "\n" + Statistics.toString();
//    }
//
//    /**
//     * reference-list
//     * @param html
//     * @return
//     */
//    public String parseRef(String html) {
//        StringBuffer Ref = new StringBuffer();
//        Document dom = Jsoup.parse(html);
//        //基本信息
//        Elements elements = dom.getElementsByClass("reference-item");
//        for(Element element : elements) {
//            Ref.append(element.text() + "  " + "http://baike.baidu.com" + element.getElementsByClass("text").attr("href") + "\n");
//        }
//        return Ref.toString();
//    }
//
//    /**
//     * open-tag-item
//     * @param html
//     * @return
//     */
//    public String parseTag(String html) {
//        StringBuffer tag = new StringBuffer();
//        Document dom = Jsoup.parse(html);
//        //基本信息
//        Elements elements = dom.getElementsByClass("taglist");
//        for(Element element : elements) {
//            tag.append(element.text() + "\n");
//        }
//        return tag.toString();
//    }
//
//    public Result getResult(String url, boolean ifUseProxy) {
//        Result result = new Result();
//        //TODO：在这里修改构造函数调用参数以进行爬虫设定,在这里加入代理
//        /**
//         * @param m_timeOut 超时时间ms 默认5000
//         * @param m_referer http头部信息 默认留空字符串
//         * @param m_isUseHttps 是否开启https协议 默认开启
//         * @param m_isUseJs 是否开启JS 默认关闭
//         * @param m_outputHeaderInfo 该Client是否要输出响应头部信息
//         * @param m_proxy 代理信息
//         */
//        DownLoadHTML downLoadHTML;
//        if(ifUseProxy) {
//            downLoadHTML = new DownLoadHTML(5000, "", true, false, false, ProxyPool.getProxy());
//        } else {
//            downLoadHTML = new DownLoadHTML(5000, "", true, false, false, null);
//        }
//        downLoadHTML.initDownloadClient();
//        String html = downLoadHTML.downloadHtml(url);
//
//        result.setTitle(parseTitle(html));
//        result.setUrl(url);
//        result.setSummary(parseSummary(html));
//        result.setBasicInfo(parseBasicInfo(html));
//        result.setIndex(parseIndex(html));
//        result.setContext(parseContext(html));
//        result.setReference(parseRef(html));
//        result.setTags(parseTag(html));
//        result.setStatics(parseStatistics(html));
//
//
//        result.setUrlLink(parseLink(html));
//
//        /*System.out.println("Title:\n"+
//        parseTitle(html)+"\nSummary:\n"
//        +parseSummary(html) +"\nBasicInfo:\n"
//        +parseBasicInfo(html)+"\nIndex:\n"
//        +parseIndex(html)+"\nStatistics:\n"
//        +parseStatistics(html)+"\nRef:\n"+
//        parseRef(html) +"\nTag:\n"+
//        parseTag(html));*/
//
//        return result;
//    }
//
//    //非入口，测试用
//    public static void main(String[] args) {
//        //Thread proxyPoolThread=new Thread(new ProxyPool());
//        //proxyPoolThread.start();
//        //System.out.println("ProxyPoolThread InitComplete.");
//        long st = System.currentTimeMillis();
//
//        //HtmlParserTool test=new HtmlParserTool();
//        //Result result=test.getResult("http://baike.baidu.com/item/金陵大学历史风貌区",false);
//
//        //System.out.println(" Result:\n"+result+"Time Last:"+(System.currentTimeMillis()-st));
//        System.out.println("Time Last:" + (System.currentTimeMillis() - st));
//    }
//}
