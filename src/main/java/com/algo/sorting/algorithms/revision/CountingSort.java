package com.algo.sorting.algorithms.revision;


import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] values = new int[]{4, 5, 7, 6, 1};
        int[] result = sort(values);
        System.out.println(Arrays.toString(result));
    }

    private static int[] sort(int[] values) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int idx = 0; idx < values.length; idx++) {
            max = Math.max(max, values[idx]);
            min = Math.min(min, values[idx]);
        }
        int[] indexes = new int[max - min + 1];
        for (int idx = 0; idx < values.length; idx++) {
            indexes[values[idx] - min]++;
        }
        for (int idx = 1; idx < indexes.length; idx++) {
            indexes[idx] += indexes[idx - 1];
        }
        int[] sorted = new int[values.length];
        for (int idx = 0; idx < values.length; idx++) {
            sorted[indexes[values[idx] - 1]-- - 1] = values[idx];
        }
        return sorted;
    }
}
