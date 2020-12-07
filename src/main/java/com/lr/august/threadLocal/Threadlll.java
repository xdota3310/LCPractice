package com.august.threadLocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * ThreadLocal
 *
 * @author shijie.xu
 * @since 2019年08月26日
 */
public class Threadlll {

    public static void main(String[] args) {
        SafeDate.get();
    }

    static class SafeDate {
        static final ThreadLocal<DateFormat> THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        static DateFormat get() {
            return THREAD_LOCAL.get();
        }


    }
}
