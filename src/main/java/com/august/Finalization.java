package com.august;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月13日
 */
public class Finalization {
    public static Finalization finalization;

    @Override
    protected void finalize() {
        System.out.println("1111");
        finalization = this;
    }


    public static void main(String[] args) {
        Finalization finalization = new Finalization();
        Finalization finalization2 = new Finalization();
        Finalization finalization3 = new Finalization();
        Finalization finalization4 = new Finalization();
        Finalization finalization5 = new Finalization();
        System.out.println(finalization);
        System.out.println(finalization2);
        System.out.println(finalization3);
        System.out.println(finalization4);
        System.out.println(finalization5);

        finalization2 = null;
        System.gc();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(finalization);
        System.out.println(Finalization.finalization);

        String s = new String();
        ReferenceQueue rq = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(s, rq);

    }
}
