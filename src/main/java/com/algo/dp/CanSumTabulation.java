package com.algo.dp;

public class CanSumTabulation {
    public static void main(String[] args) {
        boolean result = canSum(10, new int[]{2});
        System.out.println(result);
    }

    private static boolean canSum(int sum, int[] values) {
        boolean[] sums = new boolean[sum];
        for (int value : values) {
            if (sum - value == 0) {
                return true;
            }
            if (sum - value > 0) {
                sums[sum - value] = true;
            }
        }
        for (int idx = sums.length - 1; idx >= 0; idx--) {
            if (sums[idx]) {
                for (int value : values) {
                    if (idx - value == 0) {
                        return true;
                    }
                    if (idx - value > 0) {
                        sums[idx - value] = true;
                    }
                }
            }
        }
        return false;
    }
}
