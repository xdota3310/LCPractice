package com.december.patternsDesign.model;

/**
 * 模板模式
 *
 * @author shijie.xu
 * @since 2019年12月16日
 */
public abstract class Model {
    abstract void do1();
    abstract void do2();
    abstract void do3();
    abstract void do4();
    void run(){
        this.do1();
        this.do2();
        this.do3();
        this.do4();
    }
}
