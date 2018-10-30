package com.company.t1;

/**
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i;
 * and a non-negative integer fee representing a transaction fee.
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 * Return the maximum profit you can make.
 *
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * Note:
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * @author shijie.xu
 * @since 2018年10月30日
 */
public class Solution {
    public static int maxProfit(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }

    public static void main(String[] args) {
        int [] a= {1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(a,2));
    }
}

/**
 * some notes below to help me understand it:
 *
 * 1.Only 1 share of the stock can be bought or sold;
 * 2.A stock can be bought or sold for multiple times in one day, but it has to be sold before being bought again;
 * 3.The service fee is only charged when stock is sold;
 * 4.Cash(i): the cash in hand, if you are not holding the stock at the end of day(i):
 *   4.1.You might be not holding the stock at the end of day(i-1), and do nothing in day(i): a = cash(i-1); or
 *   4.2.You might be holding the stock at the end of day(i-1), and sell it at the end of day(i):
 *       b = hold(i-1) + prices[i] - fee;
 *   4.3.Choose the greatest one as the value of cash(i) to get the greater potential profit:
 *       cash(i) = max(a, b) = max(cash(i-1), hold(i-1) + prices[i] - fee);
 * 5.Hold(i): the cash in hand, if you are holding the stock at the end of day(i):
 *   5.1.You might be holding the stock at the end of day(i-1), and do nothing in day(i): a = hold(i-1); or
 *   5.2.You might be not holding the stock at the end of day(i-1), and buy it at the end of day(i): b = cash(i-1) - prices[i]; or
 *   5.3.You might be holding the stock at the end of day(i-1), sell it on day(i), and buy it again at the end of day(i):
 *       c = (hold(i-1) + prices[i] - fee) - prices[i];
 *   5.4.Choose the greatest one as the value of hold(i) to get the greater potential profit:
 *       hold(i) = max(a,b,c)
 *       Because max(b, c) = max(cash(i-1), hold(i-1) + prices[i] - fee) - prices[i] = cash(i) - prices[i],
 *       so hold(i) = max(hold(i-1), cash(i) - prices[i]);
 * */

