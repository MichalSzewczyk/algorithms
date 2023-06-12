package com.algo.sorting.algorithms.revision;

import java.util.Arrays;


public class Quick {
    public static void main(String[] args) {
        int[] numbers = new int[]{2, 1, 3, 2, 4};
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void sort(int[] numbers) {
        sortRecursively(numbers, 0, numbers.length - 1);
    }

    private static void sortRecursively(int[] numbers, int start, int end) {
        if(start < end) {
            int pivotValue = numbers[start];
            int leftIdx = start + 1;
            int rightIdx = end;
            while (leftIdx < rightIdx) {
                while (numbers[leftIdx] < pivotValue && leftIdx < rightIdx) {
                    leftIdx++;
                }
                while (numbers[rightIdx] >= pivotValue && leftIdx < rightIdx) {
                    rightIdx--;
                }
                swap(numbers, leftIdx, rightIdx);
            }
            if (numbers[leftIdx] > pivotValue) {
                leftIdx--;
            }
            swap(numbers, start, leftIdx);
            sortRecursively(numbers, start, leftIdx - 1);
            sortRecursively(numbers, leftIdx + 1, end);
        }
    }

    private static void swap(int[] numbers, int leftIdx, int rightIdx) {
        int tmp = numbers[leftIdx];
        numbers[leftIdx] = numbers[rightIdx];
        numbers[rightIdx] = tmp;

    }
}
