package com.algo.sorting.algorithms.revision;

import java.util.Arrays;


public class HeapSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{2, -1, 1};
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void sort(int[] numbers) {
        buildHeap(numbers);
        for (int idx = 0; idx < numbers.length; idx++) {
            int sortedIdx = numbers.length - idx - 1;
            swap(numbers, 0, sortedIdx);
            positionFirst(numbers, sortedIdx);
        }
    }

    private static void buildHeap(int[] numbers) {
        for (int idx = 1; idx < numbers.length; idx++) {
            int current = idx;
            int parent = idx / 2;
            while (current > 0 && numbers[current] > numbers[parent]) {
                swap(numbers, current, parent);
                current = parent;
                parent /= 2;
            }
        }
    }

    private static void positionFirst(int[] numbers, int idx) {
        int currentIdx = 0;
        int leftChildIdx = currentIdx * 2 + 1;
        while (leftChildIdx < idx) {
            int maxChild;
            if (leftChildIdx + 1 < idx) {
                maxChild = numbers[leftChildIdx] > numbers[leftChildIdx + 1] ? leftChildIdx : leftChildIdx + 1;
            } else {
                maxChild = leftChildIdx;
            }
            if (numbers[maxChild] > numbers[currentIdx]) {
                swap(numbers, maxChild, currentIdx);
                currentIdx = maxChild;
                leftChildIdx = currentIdx * 2 + 1;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] numbers, int firstIdx, int secondIdx) {
        int tmp = numbers[firstIdx];
        numbers[firstIdx] = numbers[secondIdx];
        numbers[secondIdx] = tmp;
    }
}
