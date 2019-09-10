package com.august.atomic;

import java.math.BigDecimal;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月22日
 */
public class UpdateReference {
    private int id;
    private BigDecimal price;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(5);
        UpdateReference updateReference = new UpdateReference();
        CyclicBarrier cb = new CyclicBarrier(4);
        final Object lock = new Object();
        es.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100000; i++) {
                    synchronized(lock) {
                        updateReference.setId(updateReference.getId() + 1);
                    }
                }
                System.out.println(updateReference.getId());
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100001; i++) {
                    synchronized(lock) {
                        updateReference.setId(updateReference.getId() + 1);
                    }
                }
                System.out.println(updateReference.getId());
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100002; i++) {
                    synchronized(lock) {
                        updateReference.setId(updateReference.getId() + 1);
                    }
                }
                System.out.println(updateReference.getId());
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(3000);
        cb.await();
        System.out.println(updateReference.getId());
    }
}
