package com.company.P2018_11_28;

import java.util.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年11月28日
 */
public class Sorts {
    public static void main(String[] args) {
        List<String> sss= new LinkedList<>();
        sss.add("1");
        sss.add("2");
        ((LinkedList<String>) sss).addFirst("0");
        sss.add("3");
        ((LinkedList<String>) sss).addLast("0");
        Iterator<String> s= sss.listIterator();
        for(;s.hasNext();){
            System.out.println(s.next());

        }
//        Collections.sort(sss);
    }
}
