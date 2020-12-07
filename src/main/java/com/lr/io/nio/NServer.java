package com.lr.december.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio server 78654
 *
 * @author shijie.xu
 * @since 2019年12月16日
 */
public class NServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                listenerChannel.bind(new InetSocketAddress(78654));
                listenerChannel.configureBlocking(false);
                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while(true) {
                    if(serverSelector.select() > 0) {
                        Set<SelectionKey> selectionKeySet = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = selectionKeySet.iterator();

                        while(keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();

                            if(key.isAcceptable()) {
                                try {
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                } finally {
                                    keyIterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        executorService.submit(() -> {
            try {
                while(true) {
                    if(clientSelector.select(1) > 0) {

                        Set<SelectionKey> selectionKeySet = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = selectionKeySet.iterator();
                        while(keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if(key.isReadable()) {
                                try {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                                    clientChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(new String(byteBuffer.array()));
                                } finally {
                                    keyIterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }


                    }
                }
            } catch (Exception e) {

            }
        });


    }
}
