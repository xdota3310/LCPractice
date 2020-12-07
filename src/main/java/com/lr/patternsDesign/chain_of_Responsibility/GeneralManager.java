package com.lr.patternsDesign.chain_of_Responsibility;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月14日
 */

public class GeneralManager extends Leader {
    public GeneralManager(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void handleRequest(MoneyRequest moneyRequest) {
        if (moneyRequest.getMoney() < 2000) {
            System.out.println("GeneralManager     " + name + "    审批员工     " + moneyRequest.getName() + "    的报销请求，请求金额为     "
            + moneyRequest.getMoney());
        } else {
            System.out
            .println(moneyRequest.getName() + "    尽然敢报销这个金额     " + moneyRequest.getMoney() + "    不想混了，看来！");
        }
    }
}

