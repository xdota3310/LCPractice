package com.december.io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * bio client
 *
 * @author shijie.xu
 * @since 2019年12月16日
 */
public class BClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6846);
        try {

            for(int i = 0; i < 5; i++) {
                socket.getOutputStream().write("12312".getBytes());
                socket.getOutputStream().flush();
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            socket.close();
        }
    }
}
