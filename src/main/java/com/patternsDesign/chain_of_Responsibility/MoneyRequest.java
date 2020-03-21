package com.patternsDesign.chain_of_Responsibility;

/**
 * 首先创建一个报销类，里面包含这个业务的一些具体信息 包括姓名 费用等
 *
 * @author shijie.xu
 * @since 2019年12月14日
 */

public class MoneyRequest {

    private String name;
    private double money;

    public MoneyRequest(String name, double money) {
        super();
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}

