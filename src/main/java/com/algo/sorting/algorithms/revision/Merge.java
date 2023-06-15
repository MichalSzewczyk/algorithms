package com.algo.sorting.algorithms.revision;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] values = new int[]{5, 4, 3, 2, 1};
        sort(values);
        System.out.println(Arrays.toString(values));
    }

    private static void sort(int[] values) {
        sortRecursively(values, 0, values.length - 1);
    }

    private static void sortRecursively(int[] values, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            sortRecursively(values, start, mid);
            sortRecursively(values, mid + 1, end);
            merge(values, start, mid + 1, end);
        }
    }

    private static void merge(int[] values, int leftIdx, int rightIdx, int end) {
        int start = leftIdx;
        int leftEnd = rightIdx - 1;
        int[] result = new int[end - leftIdx + 1];
        for (int idx = 0; idx < result.length; idx++) {
            if (rightIdx > end) {
                result[idx] = values[leftIdx++];
            } else if (leftIdx > leftEnd) {
                result[idx] = values[rightIdx++];
            } else if (values[leftIdx] <= values[rightIdx]) {
                result[idx] = values[leftIdx++];
            } else if (values[leftIdx] > values[rightIdx]) {
                result[idx] = values[rightIdx++];
            }
        }
        for (int idx = 0; idx < result.length; idx++) {
            values[start + idx] = result[idx];
        }
    }
}
