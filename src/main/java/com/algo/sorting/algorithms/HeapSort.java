package com.algo.sorting.algorithms;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 3, 5, 2, 4, 7};
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void sort(int[] numbers) {
        for (int idx = 1; idx < numbers.length; idx++) {
            int current = idx;
            while (current != 0 && numbers[current / 2] < numbers[current]) {
                int tmp = numbers[current / 2];
                numbers[current / 2] = numbers[current];
                numbers[current] = tmp;
                current /= 2;
            }
        }
        for (int idx = 0; idx < numbers.length; idx++) {
            int sortedIdx = numbers.length - idx - 1;
            int tmp = numbers[sortedIdx];
            numbers[sortedIdx] = numbers[0];
            numbers[0] = tmp;
            insert(numbers, sortedIdx);
        }
    }

    private static void insert(int[] numbers, int sortedIdx) {
        int idx = 0;
        while ((Math.pow(2, idx)) < sortedIdx) {
            int leftChildIdx = (int) Math.pow(2, idx);
            int maxChildIdx;
            if(leftChildIdx + 1  < sortedIdx) {
                int rightChildIdx = leftChildIdx + 1;
                maxChildIdx = numbers[leftChildIdx] > numbers[rightChildIdx] ? leftChildIdx : rightChildIdx;
            } else {
                maxChildIdx = leftChildIdx;
            }
            if (numbers[idx] < numbers[maxChildIdx]) {
                int tmp = numbers[idx];
                numbers[idx] = numbers[maxChildIdx];
                numbers[maxChildIdx] = tmp;
                idx = maxChildIdx;
            } else {
                return;
            }
        }
    }
}