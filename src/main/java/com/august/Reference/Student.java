package com.august.Reference;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月13日
 */
public class Student {
    private String name;
    private String code;

    public Student(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", code='" + code + '\'' + '}';
    }

    @Override
    protected void finalize(){
        System.out.println(this.toString());
    }
}
