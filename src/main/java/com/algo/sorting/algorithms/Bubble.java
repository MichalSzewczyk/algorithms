package com.algo.sorting.algorithms;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int[] values = new int[]{7, 1, 2, 5, 3};
        sort(values);
        System.out.println(Arrays.toString(values));
    }

    private static void sort(int[] values) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int idx = 1; idx < values.length; idx++) {
                if (values[idx - 1] > values[idx]) {
                    int tmp = values[idx];
                    values[idx] = values[idx - 1];
                    values[idx - 1] = tmp;
                    swapped = true;
                }
            }
        }
    }
}
