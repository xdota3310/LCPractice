package com.august.compleFuture;

import java.util.concurrent.CompletableFuture;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月23日
 */
public class CompleFuture {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {

        });
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {

        });

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {

            return "";
        });

        CompletableFuture<String> future2 = future.thenCombine(future1, (aVoid, s) -> {
            return "" + s;
        });
    }

}
