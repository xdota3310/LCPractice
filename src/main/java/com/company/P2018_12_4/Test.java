package com.company.P2018_12_4;


import java.util.HashMap;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年12月04日
 */
public class Test {

    public static void main(String[] args) {
        Testcla a=new Testcla("1","2");
        Testcla b=new Testcla("1","2");
        System.out.println(a==b);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
/*        System.out.println(a.hashCode());
        HashMap<Testcla,Integer> hashMap=new HashMap<>(16);
        hashMap.put(a,3);
        System.out.println(hashMap.get(new Testcla("1","2")));*/

    }
}
