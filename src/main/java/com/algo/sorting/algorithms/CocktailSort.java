package com.algo.sorting.algorithms;

import java.util.Arrays;

public class CocktailSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 3, 4, 2};
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void sort(int[] numbers) {
        boolean swapped = true;
        boolean isLeft = true;
        while (swapped) {
            swapped = false;
            if (isLeft) {
                for (int idx = 1; idx < numbers.length; idx++) {
                    if (numbers[idx] < numbers[idx - 1]) {
                        swap(numbers, idx, idx - 1);
                        swapped = true;
                    }
                }
            }
            if (!isLeft) {
                for (int idx = numbers.length - 1; idx >= 1; idx--) {
                    if (numbers[idx] < numbers[idx - 1]) {
                        swap(numbers, idx, idx - 1);
                        swapped = true;
                    }
                }
            }
            isLeft = !isLeft;
        }
    }

    private static void swap(int[] numbers, int firstIdx, int secondIdx) {
        int tmp = numbers[firstIdx];
        numbers[firstIdx] = numbers[secondIdx];
        numbers[secondIdx] = tmp;
    }
}
