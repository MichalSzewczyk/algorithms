package com.algo.sherlockandcost;

public class Algorithm {
    /**
     * Link to description: https://www.hackerrank.com/challenges/sherlock-and-cost/problem
     */
    public static void main(String[] args) {
        int sum = countMaxSum(new int[]{1, 2, 3});
        System.out.println(sum);
    }

    public static int countMaxSum(int[] values) {
        int[][] sums = new int[values.length][];
        for (int idx = 0; idx < values.length; idx++) {
            sums[idx] = new int[2];
        }
        for (int idx = 0; idx < 2; idx++) {
            sums[0][idx] = 0;
        }
        for (int sumsIdx = 1; sumsIdx < sums.length; sumsIdx++) {
            for (int valueIdx = 0; valueIdx < 2; valueIdx++) {
                int maxDiff = 0;
                for (int prevValueIdx = 0; prevValueIdx < 2; prevValueIdx++) {
                    int value;
                    int prevValue;
                    if (valueIdx == 0) {
                        value = 1;
                    } else {
                        value = values[sumsIdx];
                    }
                    if (prevValueIdx == 0) {
                        prevValue = 1;
                    } else {
                        prevValue = values[sumsIdx - 1];
                    }
                    maxDiff = Math.max(maxDiff, Math.abs(value - prevValue) + sums[sumsIdx - 1][prevValueIdx]);
                }
                sums[sumsIdx][valueIdx] = maxDiff;
            }
        }
        int result = 0;
        for (int idx = 0; idx < sums[sums.length - 1].length; idx++) {
            result = Math.max(result, sums[sums.length - 1][idx]);
        }
        return result;
    }

}
