package com.baidubaikespider.crawler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

import com.baidubaikespider.common.URLFilter;
//import com.baidubaikespider.db.dao.ResultDao;
import com.baidubaikespider.proxypool.ProxyPool;

/**
 */
public class CrawlerStart {

    /**
     * 使用了布隆过滤算法的URL去重
     */
    public static URLFilter urlFilter = new URLFilter();
    /**
     * 爬虫线程池
     */
    public static ExecutorService crawlerThreadPool = Executors.newFixedThreadPool(20);
//    new ThreadPoolExecutor(10, 20, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
    /**
     * 结果队列，爬到的HTML并解析的结果放在这里
     */
    public static Queue<Result> resultsQueue = new LinkedList<>();

    /**
     * 弃用，任务计数器
     */
    public static int taskCounter = 0;
    /**
     * 弃用，最大任务数
     */
    public static final int MAX_TASK_NUM = 1000;

    /**
     * 是否使用代理池进行爬虫
     */
    public static boolean ifUseProxy = false;
    //public static Result queueTop = new Result();


    /**
     * 由一个url初始化爬虫
     *
     * @param url
     * @return
     */
    public CrawlerStart initial(String url) {
        HtmlParserTool parserTool = new HtmlParserTool();
        //将种子爬虫加入结果队列
        resultsQueue.add(parserTool.getResult(url, ifUseProxy));
        //将种子url加入去重filter
        urlFilter.add(url);
        return this;
    }

    public void launch() {
        //将种子爬取结果进行数据持久化
//        ResultDao.persistent(resultsQueue.peek());
        while(resultsQueue.size() > 0) {

            System.out.println("*********Already Crawled: " + Crawler.CrawlerID + " lemmas*********");
            //从任务队列取出一个结果
            Result resultInQueueTop = resultsQueue.poll();
            //创建任务列表
            ArrayList<FutureTask<Result>> tasks = new ArrayList<>();

            //将爬取结果中的链接取出，查询是否爬过，没有则把url加入去重filter并加入任务列表
            for(String link : resultInQueueTop.getUrlLink()) {
                //若没有爬取过这个页面
                if(!urlFilter.contains(link)) {
                    urlFilter.add(link);
                    tasks.add(new FutureTask<>(new Crawler(link)));
                }
            }
            //将任务列表中的任务提交给线程池进行处理
            for(FutureTask<Result> task : tasks) {
                //线程池执行数组的线程
                crawlerThreadPool.submit(task);
            }
            //TODO: Not Tested,Maybe Dangerous
            //taskCounter += tasks.size();
            //等待任务列表中的任务全部完成，完成后将结果放入结果队列准备下次取出，并把结果数据持久化
            for(FutureTask<Result> task : tasks) {
                try {
                    Result taskResult;
                    //获取任务的结果,设置每个任务的超时时间为1s
                    taskResult = task.get(5, TimeUnit.SECONDS);
                    taskCounter--;
                    //过滤掉误爬的链接
                    if(!"".equals(taskResult.getTitle())) {
                        System.out.println(taskResult.getTitle());
                        resultsQueue.add(taskResult);
                        System.out.println(taskResult.toString());
//                        ResultDao.persistent(taskResult);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        if(args.length != 0) {
            if("EnableProxy".equals(args[0])) {
                ifUseProxy = true;
            } else if("DisableProxy".equals(args[0])) {
                ifUseProxy = false;
            }
        }
        //如果使用代理，则开启代理池线程
        if(ifUseProxy) {
            ProxyPool proxyPool = new ProxyPool();
            proxyPool.initProxyPool();
            Thread proxyPoolThread = new Thread(proxyPool);
            proxyPoolThread.start();
        }
        long st = System.currentTimeMillis();
        new CrawlerStart().initial("http://baike.baidu.com/item/四川大学").launch();
        //new CrawlerStart().initial("https://baike.baidu.com/view/21087.html").launch();
        System.out.println("\n爬完总共花费时间：" + (System.currentTimeMillis() - st));
    }

}
