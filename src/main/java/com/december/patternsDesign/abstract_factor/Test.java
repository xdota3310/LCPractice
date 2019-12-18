package com.december.patternsDesign.abstract_factor;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月16日
 */
public class Test {
    public static void main(String[] args) {
        AbstractFactory winFactory = new WinFactory();
        winFactory.doFile();
        winFactory.doSocket();
    }
}
