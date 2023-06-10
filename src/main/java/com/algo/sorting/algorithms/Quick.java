package com.algo.sorting.algorithms;

import java.util.Arrays;

public class Quick {
    public static void main(String[] args) {
        int[] values = new int[]{3, 1, 2};
        sort(values);
        System.out.println(Arrays.toString(values));
    }

    private static void sort(int[] values) {
        sortRecursively(values, 0, values.length - 1);
    }

    private static void sortRecursively(int[] values, int startIdx, int endIdx) {
        if (startIdx < endIdx) {
            int pivot = values[startIdx];
            int leftIdx = startIdx + 1;
            int rightIdx = endIdx;
            while (leftIdx != rightIdx) {
                while (values[leftIdx] < pivot && leftIdx < rightIdx) {
                    leftIdx++;
                }
                while (values[rightIdx] >= pivot && leftIdx < rightIdx) {
                    rightIdx--;
                }
                if (leftIdx < rightIdx) {
                    int tmp = values[leftIdx];
                    values[leftIdx] = values[rightIdx];
                    values[rightIdx] = tmp;
                }
            }
            if (values[leftIdx] > values[startIdx]) {
                leftIdx--;
            }
            int tmp = values[startIdx];
            values[startIdx] = values[leftIdx];
            values[leftIdx] = tmp;
            sortRecursively(values, startIdx, leftIdx - 1);
            sortRecursively(values, leftIdx + 1, endIdx);
        }
    }
}
