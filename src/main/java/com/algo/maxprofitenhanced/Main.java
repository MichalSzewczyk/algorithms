package com.algo.maxprofitenhanced;

public class Main {
    public static void main(String[] args) {
        int[] prices = {4, 1, 2, 1, 2, 3, 2, 4, 6};
        int maxProfitEnhanced = getMaxProfit(prices);
        System.out.println(maxProfitEnhanced);
    }

    /**
     * Buy/sell twice
     */
    private static int getMaxProfitTwice(int[] prices) {
        throw new UnsupportedOperationException();
    }

    /**
     * Buy/sell without limit
     */
    private static int getMaxProfitLimitless(int[] prices) {
        int profit = 0;
        for (int index = 1; index < prices.length; index++) {
            if (prices[index] > prices[index - 1]) {
                profit += prices[index] - prices[index - 1];
            }
        }
        return profit;
    }

    /**
     * Buy once, sell once
     */
    private static int getMaxProfit(int[] prices) {
        int max = prices[0];
        int min = prices[0];
        int profit = 0;
        for (int index = 1; index < prices.length; index++) {
            if (min > prices[index]) {
                int currentProfit = max - min;
                if (profit < currentProfit) {
                    profit = currentProfit;
                }
                min = prices[index];
                max = min;
            } else if (prices[index] > max) {
                max = prices[index];
            }
        }
        if (max - min > profit) {
            return max - min;
        } else {
            return profit;
        }
    }
}
