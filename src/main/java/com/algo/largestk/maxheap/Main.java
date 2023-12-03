package com.algo.largestk.maxheap;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int k = 5;
        int[] numbers = new int[]{k, 2, 3, 4, 1};
        int result = getKthLargestElement(k, numbers);
        System.out.println(result);
    }

    private static int getKthLargestElement(int k, int[] numbers) {
        if (k <= 0 || numbers.length == 0) {
            throw new IllegalArgumentException(String.format("Unable to find kth element. [k=%s] [numbers=%s]",
                    k, Arrays.toString(numbers)));
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int number : numbers) {
            if (queue.size() >= k) {
                int smallest = queue.peek();
                if (smallest < number) {
                    queue.add(number);
                    queue.remove(smallest);
                }
            } else {
                queue.add(number);
            }
        }
        return queue.peek();
    }
}
