package com.lr.July.JVM;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年07月29日
 */
public class Code {
    static {
        System.out.println("自定义加载器测试类！");
    }

    public static void main(String[] args) {
        int i=1,j=5;
        i++;
        ++j;
        System.out.println(i);
        System.out.println(j);
    }


}
