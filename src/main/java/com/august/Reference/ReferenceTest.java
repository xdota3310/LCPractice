package com.august.Reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月13日
 */
public class ReferenceTest {
    private static ReferenceQueue<Student> queue = new ReferenceQueue<>();

    public static void check() {
        Reference<Student> ref = null;
        while((ref = (Reference<Student>) queue.poll()) != null) {
            System.out.println("In queue:" + ((StudentWeakReference) (ref)).getName());
            System.out.println("reference object:" + ref.get());
        }
    }

    public static void main(String[] args) {
        List<WeakReference<Student>> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(new StudentWeakReference(new Student(String.valueOf(i),String.valueOf(i)),queue));
        }
        System.out.println("first");
        check();
        System.gc();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("second");
        check();
    }
}
