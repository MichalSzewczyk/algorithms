package com.algo.sorting.algorithms;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 3, 5, 2, 4, 7};
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void sort(int[] numbers) {
        buildHeap(numbers);
        for (int idx = 0; idx < numbers.length; idx++) {
            int sortedIdx = numbers.length - idx - 1;
            int tmp = numbers[sortedIdx];
            numbers[sortedIdx] = numbers[0];
            numbers[0] = tmp;
            positionRootElement(numbers, sortedIdx);
        }
    }

    private static void buildHeap(int[] numbers) {
        for (int idx = 1; idx < numbers.length; idx++) {
            int currentIdx = idx;
            int parentIdx = currentIdx / 2;
            while (currentIdx != 0 && numbers[parentIdx] < numbers[currentIdx]) {
                swapElements(numbers, currentIdx, parentIdx);
                currentIdx /= 2;
                parentIdx /= 2;
            }
        }
    }

    private static void positionRootElement(int[] numbers, int sortedIdx) {
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
                swapElements(numbers, idx, maxChildIdx);
                idx = maxChildIdx;
            } else {
                return;
            }
        }
    }

    private static void swapElements(int[] numbers, int firstIdx, int secondIdx) {
        int tmp = numbers[firstIdx];
        numbers[firstIdx] = numbers[secondIdx];
        numbers[secondIdx] = tmp;
    }
}