package com.algo.sorting.algorithms;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] numbers = new int[]{4, 1, 2, 3};
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void sort(int[] numbers) {
        sortRecursively(numbers, 0, numbers.length - 1);
    }

    private static void sortRecursively(int[] numbers, int start, int end) {
        if (start < end) {
            int mid = (end + start) / 2;
            sortRecursively(numbers, start, mid);
            sortRecursively(numbers, mid + 1, end);
            merge(numbers, start, mid, end);
        }
    }

    private static void merge(int[] numbers, int start, int mid, int end) {
        int[] merged = new int[end - start + 1];
        int leftIdx = start;
        int rightIdx = mid + 1;
        for (int idx = 0; idx < merged.length; idx++) {
            if (rightIdx > end) {
                merged[idx] = numbers[leftIdx++];
            } else if (leftIdx > mid) {
                merged[idx] = numbers[rightIdx++];
            } else if (numbers[leftIdx] <= numbers[rightIdx]) {
                merged[idx] = numbers[leftIdx++];
            } else if (numbers[leftIdx] >= numbers[rightIdx]) {
                merged[idx] = numbers[rightIdx++];
            }
        }
        for (int idx = 0; idx < merged.length; idx++) {
            numbers[start + idx] = merged[idx];
        }
    }
}
