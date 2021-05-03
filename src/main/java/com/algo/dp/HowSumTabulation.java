package com.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HowSumTabulation {
    public static void main(String[] args) {
        int[] arr = howSum(10, new int[]{2});
        System.out.println(Arrays.toString(arr));
    }

    private static int[] howSum(int sum, int[] values) {
        int[] indexes = new int[sum + 1];
        indexes[0] = 1;
        boolean shouldContinue = true;
        for (int idx = 0; idx <= sum; idx++) {
            if (!shouldContinue) {
                break;
            }
            if (indexes[idx] > 0) {
                for (int value : values) {
                    if (idx + value < indexes.length) {
                        if (indexes[idx + value] == 0) {
                            indexes[idx + value] = value;
                        }
                    }
                    if (idx + value == indexes.length - 1) {
                        shouldContinue = false;
                        break;
                    }
                }
            }
        }
        int currentIdx = indexes.length - 1;
        List<Integer> results = new ArrayList<>();
        if (indexes[currentIdx] == 0) {
            return new int[0];
        }
        while (currentIdx != 0) {
            int currentValue = indexes[currentIdx];
            results.add(currentValue);
            currentIdx -= currentValue;
        }
        return results.stream().mapToInt(value -> value).toArray();
    }
}
