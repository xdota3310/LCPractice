package com.december.chain_of_Responsibility;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月14日
 */

public class Director extends Leader {

    public Director(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void handleRequest(MoneyRequest moneyRequest) {
        if (moneyRequest.getMoney() < 300) {
            System.out.println("Director   " + name + "   审批员工" + moneyRequest.getName() + "   的报销请求，请求金额为     "
            + moneyRequest.getMoney());
        } else {
            this.successor.handleRequest(moneyRequest);
        }
    }

}

