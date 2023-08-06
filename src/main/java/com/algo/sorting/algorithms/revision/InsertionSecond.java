package com.algo.sorting.algorithms.revision;

import java.util.Arrays;

public class InsertionSecond {
    public static void main(String[] args) {
        int[] values = new int[]{4, 3, 2, 1};
        insertionSort(values);
        System.out.println(Arrays.toString(values));
    }

    private static void insertionSort(int[] values) {
        if (values.length > 0) {
            int idx = 1;
            while (idx < values.length) {
                if (values[idx - 1] > values[idx]) {
                    int swapIdx = idx;
                    while (swapIdx > 0 && values[swapIdx - 1] > values[swapIdx]) {
                        int tmp = values[swapIdx];
                        values[swapIdx] = values[swapIdx - 1];
                        values[swapIdx - 1] = tmp;
                        swapIdx--;
                    }
                }
                idx++;
            }
        }
    }
}
