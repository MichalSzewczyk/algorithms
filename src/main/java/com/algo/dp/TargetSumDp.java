package com.algo.dp;

import java.util.HashSet;

public class TargetSumDp {
    /**
     * Given input:
     * target_sum   - int value
     * values       - array of int values
     * Implement sum algorithm to check if target_sum can be represented as sum of values (values can be repeated)
     */
    public static void main(String[] args) {
        boolean result = sum(7, new int[]{5, 3, 4, 7}, new HashSet<>());
        System.out.println(result);
    }

    private static boolean sum(int targetSum, int[] values, HashSet<Object> checkedSums) {
        if (checkedSums.contains(targetSum)) {
            return false;
        } else if (targetSum == 0) {
            return true;
        } else if (targetSum < 0) {
            return false;
        } else {
            for (int value : values) {
                boolean currentResult = sum(targetSum - value, values, checkedSums);
                if (currentResult) {
                    return true;
                }
            }
            checkedSums.add(targetSum);
            return false;
        }
    }
}
