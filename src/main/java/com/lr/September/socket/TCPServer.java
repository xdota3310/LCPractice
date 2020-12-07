package com.lr.September.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年09月04日
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(6657);
        while(true) {
            Socket s = ss.accept();
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();
            int ch = 0;
            byte[] bytes = new byte[1024];
            ch = is.read(bytes);
            String content = new String(bytes,0,ch);
            System.out.println(content);
            os.write(String.valueOf(content.length()).getBytes());
            is.close();
            os.close();
//            ss.close();

        }
    }
}
