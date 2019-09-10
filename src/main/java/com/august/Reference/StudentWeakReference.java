package com.august.Reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月13日
 */
public class StudentWeakReference extends WeakReference<Student> {
    private String name;

    public StudentWeakReference(Student referent, ReferenceQueue<? super Student> q) {
        super(referent, q);
        this.name = referent.getName();
    }

    public String getName() {
        return name;
    }

    @Override
    protected void finalize() {
        System.out.println(this.getClass().getName()+":"+name);
    }
}
