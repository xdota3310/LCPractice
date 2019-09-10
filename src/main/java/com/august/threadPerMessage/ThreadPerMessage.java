package com.august.threadPerMessage;

import com.sun.xml.internal.ws.api.pipe.Fiber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.LockSupport;

/**
 * 并发编程领域里，解决分工问题也有一系列的设计模式
 *
 * Thread-Per-Message模式
 *
 * @author shijie.xu
 * @since 2019年08月28日
 */
public class ThreadPerMessage {
    public static void main(String[] args) {
        try {
            final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));
            try {
                while(true){
                    final SocketChannel socketChannel = ssc.accept();
                    new Thread(() ->{
                        ByteBuffer readByteBuffer = ByteBuffer.allocate(1024);
                        try {
                            socketChannel.read(readByteBuffer);
                            LockSupport.parkNanos(2000*1000000);
                            ByteBuffer writeByteBuffer = (ByteBuffer)readByteBuffer.flip();
                            socketChannel.write(writeByteBuffer);
                            socketChannel.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });

                }

            } finally {
                ssc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
