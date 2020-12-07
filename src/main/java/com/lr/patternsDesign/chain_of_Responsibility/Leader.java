package com.lr.patternsDesign.chain_of_Responsibility;

/**
 * 下面是一个抽象类 抽象的审批对象,处理报销请求
 *
 * @author shijie.xu
 * @since 2019年12月14日
 */

public abstract class Leader {

    protected String name;
    protected Leader successor;

    public Leader(String name) {
        this.name = name;
    }

    public void Setsuccessor(Leader successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(MoneyRequest moneyRequest);

}
