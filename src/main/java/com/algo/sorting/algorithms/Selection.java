package com.algo.sorting.algorithms;

import java.util.Arrays;

public class Selection {
    public static void main(String[] args) {
        int[] values = new int[]{7, 4, 5, 6, 1};
        sort(values);
        System.out.println(Arrays.toString(values));
    }

    private static void sort(int[] values) {
        for (int sortedIdx = 0; sortedIdx < values.length; sortedIdx++) {
            int minIdx = sortedIdx;
            for (int idx = sortedIdx + 1; idx < values.length; idx++) {
                if (values[idx] < values[minIdx]) {
                    minIdx = idx;
                }
            }
            int tmp = values[minIdx];
            values[minIdx] = values[sortedIdx];
            values[sortedIdx] = tmp;
        }
    }
}
