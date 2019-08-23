package com.august.dothread;

import java.util.concurrent.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月23日
 */
public class Futureff {



    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);

        ExecutorService es = new ThreadPoolExecutor(4, 4, 100000, TimeUnit.MILLISECONDS, queue);

        Count res = new Count();

        es.submit(new T1(res));
        es.submit(new T2(res));
        es.submit(new T3(res));
        es.submit(new T4(res));

        System.out.println();
    }

    static class T1 implements Runnable{
        private Count count;

        public T1(Count c) {
            this.count = c;
        }

        @Override
        public void run() {
            count.setA1(1);
            count.setA2(11);
        }
    }

    static class T2 implements Runnable{
        private Count count;

        public T2(Count c) {
            this.count = c;
        }

        @Override
        public void run() {
            count.setB1(2);
            count.setB2(22);
        }
    }

    static class T3 implements Runnable{
        private Count count;

        public T3(Count c) {
            this.count = c;
        }

        @Override
        public void run() {
            count.setC1(3);
            count.setC2(33);
        }
    }

    static class T4 implements Runnable{
        private Count count;

        public T4(Count c) {
            this.count = c;
        }

        @Override
        public void run() {
            count.setD1(4);
            count.setD2(44);
        }
    }



}
