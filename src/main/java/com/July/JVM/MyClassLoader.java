package com.July.JVM;

import java.io.*;

/**
 * 自定义类加载编写
 *
 * @author shijie.xu
 * @since 2019年07月29日
 */
public class MyClassLoader extends ClassLoader {
    private String path;
    private String classLoaderName;

    public MyClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    @Override
    public Class findClass(String name) {
        byte[] b = loadClassDate(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassDate(String name) {
        name = path + name + ".class";
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ((ByteArrayOutputStream) out).toByteArray();
    }


}
