package com.algo.maxprofitenhanced;

public class Main {
    public static void main(String[] args) {
        int[] prices = {4, 1, 2, 1, 2, 3, 2, 4, 6};
        int maxProfitEnhanced = getMaxProfit(prices);
        System.out.println(maxProfitEnhanced);
    }

    private static int getMaxProfitEnhanced(int[] prices) {
        int min = prices[0];
        int max = prices[0];
        int profit = 0;
        for (int index = 1; index < prices.length; index++) {
            if (prices[index] < max) {
                profit += max - min;
                min = prices[index];
                max = min;
            } else if (prices[index] > max) {
                max = prices[index];
            }
        }
        profit += max - min;
        return profit;
    }

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
