package com.lr.flink.flink.beaver;

import cn.yottabyte.beaver.ServiceErrorCode;
import cn.yottabyte.beaver.action.search.ClearScrollResponse;
import cn.yottabyte.beaver.action.search.SearchResponse;
import cn.yottabyte.beaver.action.search.ShardsSearchRequestBuilder;
import cn.yottabyte.beaver.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author chengtao
 * @date 21/11/2020 - 23:34
 */
public class BeaverScrollQuery {

    private static final Logger logger = LoggerFactory.getLogger(BeaverScrollQuery.class);


    private final Client client;
    private final ShardsSearchRequestBuilder reqBuilder;
    private final TimeValue keepAlive;
    private final long timeoutInMs;
    private String scrollId;
    private SearchResponse response;

    BeaverScrollQuery(Client client,
                      ShardsSearchRequestBuilder reqBuilder,
                      TimeValue keepAlive,
                      long timeoutInMs) {
        this.client = client;
        this.reqBuilder = reqBuilder;
        this.keepAlive = keepAlive;
        this.timeoutInMs = timeoutInMs;
    }

    public boolean hasNext() {
        if (this.response != null) {
            return true;
        }
        SearchResponse response;
        try {
            response = nextScroll();
        } catch (TimeoutException ex) {
            logger.error("scroll error ", ex);
            close();
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            logger.error("scroll error ", ex);
            close();
            throw new RuntimeException(ex);
        }
        if (!response.isSuccess()) {
            this.close();
        }
        if (response.getHits().getHits().length == 0) {
            return false;
        }
        this.response = response;
        return true;
    }

    public List<LogRow> next() {
        SearchResponse response = this.response;
        this.response = null;
        return new BeaverFlatExtractor().extract(response);
    }

    public void close() {
        this.response = null;
        if (scrollId == null || scrollId.isEmpty()) {
            return;
        }
        try {
            ClearScrollResponse clearScrollResp = client.prepareClearScroll().addScrollId(scrollId)
                    .execute()
                    .get(timeoutInMs, TimeUnit.MILLISECONDS);
            if (clearScrollResp.getPb().getReply().getErrorCode() != ServiceErrorCode.kOk) {
                logger.error("close scroll error " + clearScrollResp.getPbString());
                throw new RuntimeException("clear scroll '" + scrollId + "' failed");
            }
        } catch (Exception e) {
            logger.error("close scroll error ", e);
        }
        this.scrollId = null;
    }

    private SearchResponse nextScroll() throws Exception {
        SearchResponse response = null;
        if (scrollId == null || scrollId.isEmpty()) {
            reqBuilder.setScroll(keepAlive)
                    .setSid(generateSid());
            response = reqBuilder.execute().get(timeoutInMs, TimeUnit.MILLISECONDS);
            scrollId = response.getScrollId();
        } else {
            response = client.prepareScrollSearch(scrollId)
                    .setScroll(keepAlive)
                    .setSid(generateSid())
                    .execute()
                    .get(timeoutInMs, TimeUnit.MILLISECONDS);
        }
        sleep();
        return response;
    }

    private void sleep() throws Exception {
        Random random = new Random();
        long num = random.nextInt(200);
        if (num < 100) {
            num += 100;
        }
        Thread.sleep(num);
    }

    private String generateSid() {
        return String.valueOf(System.nanoTime());
    }
}
