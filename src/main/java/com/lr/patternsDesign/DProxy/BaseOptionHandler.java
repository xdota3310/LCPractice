package com.lr.patternsDesign.DProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理实现
 *
 * @author shijie.xu
 * @since 2019年12月14日
 */
public class BaseOptionHandler implements InvocationHandler {

    private Object refClass;

    public BaseOptionHandler(Object refClass) {
        this.refClass = refClass;
    }

    public Object bind() {
        return Proxy.newProxyInstance(refClass.getClass().getClassLoader(), refClass.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在调用代理的对象的真实方法前 我先调用一些自己的方法和规则..... 下面调用真实方法");

        System.out.println("Method:   " + method);

        method.invoke(refClass, args);

        System.out.println("调用代理的对象的真实方法后， 我进行一些逻辑处理 上面是调用的真实方法");

        return null;
    }


    public static void main(String[] args) {
        BaseOptionImpl subject = new BaseOptionImpl();
        BaseOptionHandler handler = new BaseOptionHandler(subject);

        // 转化成接口 只能代理实现了接口的类
        BaseOption baseOption = (BaseOption) handler.bind();
        System.out.println(baseOption.getClass().getName());

        baseOption.print();
        baseOption.printfStr("YYYYY");
    }
}
