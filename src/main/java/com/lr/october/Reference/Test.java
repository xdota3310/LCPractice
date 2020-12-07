package com.lr.october.Reference;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年10月26日
 */
public class Test {
    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();
        Map map = Collections.synchronizedMap(weakHashMap);


    }
}
