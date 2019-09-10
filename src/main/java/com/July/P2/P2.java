package com.July.P2;

import com.sun.javafx.geom.Path2D;

import java.util.Objects;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年07月02日
 */
public class P2 extends P1{
    private String name= "two";
    @Override
    public String toString() {
        this.name = "ttwo";
        super.toString();
        return super.toString()+"{" + "name='" + name + '\'' + '}';
    }

    public static void main(String[] args) {
        int i= 123456789;
        float f = i;
        int ii = (int)f;
        Integer iii = 1;
        int j = iii.intValue();
        System.out.println(i);
        System.out.println(f);
        System.out.println(ii);

        System.out.println(new P2().toString());
        System.out.println(new P1().toString());
    }
}
