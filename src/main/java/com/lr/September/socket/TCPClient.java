package com.lr.September.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年09月04日
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",6657);
        OutputStream os = s.getOutputStream();
        InputStream is = s.getInputStream();
        os.write("请填写类注释".getBytes());
        int ch = 0;
        byte[] bytes = new byte[1024];
        ch = is.read(bytes);
        System.out.println(new String(bytes,0,ch));
        is.close();
        os.close();

        s.close();


    }
}
