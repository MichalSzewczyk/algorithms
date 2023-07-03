package com.algo.sorting.algorithms.revision;

import java.util.Arrays;

public class Selection {
    public static void main(String[] args) {
        int[] values = new int[]{5, 1, 3, 2, 4};
        sort(values);
        System.out.println(Arrays.toString(values));
    }

    private static void sort(int[] values) {
        for (int current = 0; current < values.length; current++) {
            int minIdx = current;
            for (int idx = current + 1; idx < values.length; idx++) {
                if (values[minIdx] > values[idx]) {
                    minIdx = idx;
                }
            }
            int tmp = values[current];
            values[current] = values[minIdx];
            values[minIdx] = tmp;
        }
    }
}
