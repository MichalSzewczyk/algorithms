package com.algo.projecteuler;

public class FirstTask {
    /**
     * Implementation of algorithm for:
     * https://www.hackerrank.com/contests/projecteuler/challenges/euler001/problem
     */
    public static void main(String[] args) {
        int result = calculateSum(10);
        System.out.println(result);
    }

    private static int calculateSum(int n) {
        return countSum(n, 3) + countSum(n, 5) - countSum(n, 15);
    }

    private static int countSum(int n, int dividedBy) {
        int divided = (n - 1) / dividedBy;
        return dividedBy * (divided + 1) * divided / 2;
    }
}
