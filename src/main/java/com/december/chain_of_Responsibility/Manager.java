package com.december.chain_of_Responsibility;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月14日
 */

public class Manager extends Leader {

    public Manager(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void handleRequest(MoneyRequest moneyRequest) {
        if (moneyRequest.getMoney() < 800) {
            System.out.println("Manager   " + name + "   审批员工" + moneyRequest.getName() + "   的报销请求，请求金额为     "
            + moneyRequest.getMoney());
        } else {
            this.successor.handleRequest(moneyRequest);
        }
    }

    public static void main(String[] args) {
        new GeneralManager("g").handleRequest(new MoneyRequest("g",100000d));
        new Director("d").handleRequest(new MoneyRequest("d",200000d));
        new Manager("m").handleRequest(new MoneyRequest("m",20000d));
    }
}

