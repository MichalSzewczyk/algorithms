package com.algo.largest;

import java.util.PriorityQueue;

public class HeapBased {
    public static void main(String[] args) {
        int value = getKthElement(4, new int[]{3, 5, 4, 7, 1, 2});
        System.out.println(value);
    }

    private static int getKthElement(int k, int[] values) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int value : values) {
            heap.add(value);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        return heap.remove();
    }
}
