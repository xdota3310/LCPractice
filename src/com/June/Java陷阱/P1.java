package com.June.Java陷阱;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年06月17日
 */
public class P1 {
    public static void FloatPrimitiveTest(){
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        if (a == b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    public static void FloatWrapperTest (){
        Float a = Float.valueOf(1.0f - 0.9f);
        Float b = Float.valueOf(0.9f - 0.8f);
        if (a.equals(b)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    public static void SwitchTest  (){
        String param = null;
        switch (param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }

    public static void BigDecimalTest   (){
        BigDecimal a = new BigDecimal(0.1);
        System.out.println(a);
        BigDecimal b = new BigDecimal("0.1");
        System.out.println(b);
    }

    private final static Lock lock = new ReentrantLock();

    public static void LockTest    (){
        try {
            lock.tryLock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        FloatPrimitiveTest();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        FloatWrapperTest();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
//        SwitchTest();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        BigDecimalTest();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        LockTest();
    }

}
