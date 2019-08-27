package com.august.balking;

/**
 * Balking模式本质上是一种规范化地解决“多线程版本的if”的方案，使用
 * Balking模式规范化之后的写法如下所示，你会发现仅仅是将edit()方法中对共享变量changed的赋值操作抽
 * 取到了change()中，这样的好处是将并发处理逻辑和业务逻辑分开。
 *
 * @author shijie.xu
 * @since 2019年08月27日
 */
public class Balking {

}
