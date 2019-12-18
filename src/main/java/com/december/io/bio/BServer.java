package com.december.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio server
 *
 * @author shijie.xu
 * @since 2019年12月16日
 */
public class BServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6846);
            while(true){
                Socket socket = serverSocket.accept();
                byte[] data = new byte[1024];
                InputStream inputStream = socket.getInputStream();
                int len;
                while(( len = inputStream.read(data)) != -1){
                    System.out.println(new String(data));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
