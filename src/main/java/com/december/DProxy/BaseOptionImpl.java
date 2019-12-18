package com.december.DProxy;

/**
 * BaseOption实现类
 *
 * @author shijie.xu
 * @since 2019年12月14日
 */
public class BaseOptionImpl implements BaseOption {
    @Override
    public void print() {
        System.out.println("BaseOptionImpl");
    }

    @Override
    public void printfStr(String string) {
        System.out.println("BaseOptionImpl: " + string);
    }
}
