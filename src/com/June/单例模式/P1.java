package com.June.单例模式;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年06月10日
 */
public class P1 {
    private static P1 p1 = null;

    public P1 getp1() {
        if(p1 == null) {
            synchronized(P1.class) {
                if(p1 == null) {
                    p1 = new P1();
                }
            }
        }
        return p1;
    }
}
