package com.august.Ratelimiter;

import java.util.concurrent.TimeUnit;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月30日
 */
public class Limiter {
    /**
     * 下一个令牌产生的时间
     */
    long next = System.nanoTime();

    /**
     * 令牌发放间隔：nanoTime
     */
    long interval = 1_000_000_000;

    /**
     * 预占令牌，返回能够获取令牌的时间
     *
     * @param now
     * @return
     */
    synchronized long reserve(long now) {
//        请求时间在下一令牌产生时间之后
//        重新计算下一个令牌产生时间
        if(now > next) {
//            将下一个令牌产生时间重置为当前时间
            next = now;
        }
//        能够获取令牌的时间
        long at = next;
//        设置下一令牌产生时间
        next += interval;
        return Math.max(at, 0L);
    }

    /**
     * 申请令牌
     */
    void acquire() {
//        申请令牌时的时间
        long now = System.nanoTime();
//        预占令牌
        long at = reserve(now);
        long waitTime = Math.max(at - now, 0L);
//        按照条件等待
        if(waitTime > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
