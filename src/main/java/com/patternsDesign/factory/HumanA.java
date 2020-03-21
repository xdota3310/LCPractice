package com.patternsDesign.factory;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月16日
 */
public class HumanA implements Human{

    @Override
    public void talk() {
        System.out.println("a");
    }

    @Override
    public void color() {
        System.out.println("aa");
    }
}
