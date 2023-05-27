package com.algo.sorting.algorithms;

import java.util.Arrays;

public class Insertion {
    public static void main(String[] args) {
        int[] values = new int[]{3, 5, 4, 7, 1};
        int[] sorted = sort(values);
        System.out.println(Arrays.toString(sorted));
    }

    private static int[] sort(int[] values) {
        for (int idx = 1; idx < values.length; idx++) {
            int swapIdx = idx;
            while (swapIdx > 0 && values[swapIdx - 1] > values[swapIdx]) {
                int tmp = values[swapIdx - 1];
                values[swapIdx - 1] = values[swapIdx];
                values[swapIdx] = tmp;
                swapIdx--;
            }
        }
        return values;
    }
}
