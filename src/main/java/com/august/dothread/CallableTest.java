package com.august.dothread;

import java.util.concurrent.Callable;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月13日
 */
public class CallableTest implements Callable<String> {
    private static int i = 0;
    @Override
    public String call() throws Exception {
        System.out.println("Ready to work");
        Thread.sleep(3000);
        System.out.println("done");
        return String.valueOf(i++);
    }
}
