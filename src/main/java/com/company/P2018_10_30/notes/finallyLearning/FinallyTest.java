package com.company.P2018_10_30.notes.finallyLearning;

import java.util.ArrayList;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年10月30日
 */
public class FinallyTest {
    public static void main(String[] args) {
        System.out.println("return value of getValue(): " + getValue());
    }
    public static int getValue() {
        List<String> list = new ArrayList<>();
        list.remove(0);
        int i = 1;
        try {
            return i;
        } finally {
            i++;
        }
    }
}
