package com.algo.sorting.algorithms.revision;

import java.util.Arrays;

public class CocktailSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{5, 4, 3, 2};
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void sort(int[] numbers) {
        boolean swapped = true;
        boolean isLeft = true;
        int startIdx = 0;
        int endIdx = numbers.length - 1;
        while (startIdx < endIdx && swapped) {
            swapped = false;
            if (isLeft) {
                for (int idx = startIdx + 1; idx <= endIdx; idx++) {
                    if (numbers[idx - 1] > numbers[idx]) {
                        int tmp = numbers[idx - 1];
                        numbers[idx - 1] = numbers[idx];
                        numbers[idx] = tmp;
                        swapped = true;
                    }
                }
                endIdx--;
            } else {
                for (int idx = endIdx; idx >= startIdx + 1; idx--) {
                    if (numbers[idx - 1] > numbers[idx]) {
                        int tmp = numbers[idx - 1];
                        numbers[idx - 1] = numbers[idx];
                        numbers[idx] = tmp;
                        swapped = true;
                    }
                }
                startIdx++;
            }
            isLeft = !isLeft;
        }
    }
}
