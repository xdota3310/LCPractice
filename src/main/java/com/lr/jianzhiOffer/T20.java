package com.lr.jianzhiOffer;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 */
public class T20 {



    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
        System.out.println(date.toString());
        System.out.println(date.getTime());

    }
}
