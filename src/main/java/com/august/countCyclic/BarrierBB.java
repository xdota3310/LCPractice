package com.august.countCyclic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月21日
 */
public class BarrierBB {


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("11");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2");
                try {
                    Thread.sleep(10000);
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("22");
            }
        }).start();

        cb.await();
        System.out.println("main");

    }
}
