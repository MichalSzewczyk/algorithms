package com.algo.maxprofit;

public class Main {
    public static void main(String[] args) {
        double[] stockPrices = new double[]{2.1, 4.2, -1, 7.9};
        double maxProfit = calcMaxProfit(stockPrices);
        System.out.println(maxProfit);
    }

    private static double calcMaxProfit(double[] stockPrices) {
        double lowest = Double.MAX_VALUE;
        double highest;
        double maxProfit = 0;
        for (double stockPrice : stockPrices) {
            if (lowest > stockPrice) {
                lowest = stockPrice;
                highest = stockPrice;
            } else {
                highest = Math.max(stockPrice, lowest);
            }
            maxProfit = Math.max(maxProfit, highest - lowest);
        }
        return maxProfit;
    }
}
