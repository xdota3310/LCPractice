package com.lr.patternsDesign.factory;

/**
 * 抽象工厂类
 *
 * @author shijie.xu
 * @since 2019年12月16日
 */
public abstract class AbstractHumanFactory {
    public  abstract <T extends Human> T createHuman(Class<T> c);
}
