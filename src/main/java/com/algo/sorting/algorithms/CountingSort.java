package com.algo.sorting.algorithms;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{8, 7, 1, 9};
        int[] result = sort(numbers);
        System.out.println(Arrays.toString(result));
    }

    private static int[] sort(int[] numbers) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int idx = 0; idx < numbers.length; idx++) {
            minValue = Math.min(minValue, numbers[idx]);
            maxValue = Math.max(maxValue, numbers[idx]);
        }
        int[] memo = new int[maxValue - minValue + 1];
        for (int idx = 0; idx < numbers.length; idx++) {
            memo[numbers[idx] - minValue]++;
        }
        for (int idx = 1; idx < memo.length; idx++) {
            memo[idx] += memo[idx - 1];
        }
        int[] result = new int[numbers.length];
        for (int idx = 0; idx < numbers.length; idx++) {
            int position = memo[numbers[idx] - minValue]-- - 1;
            result[position] = numbers[idx];
        }
        return result;
    }
}
