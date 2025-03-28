package com.algo.google.window;

public class LargestSumOfSizeK {
    public static void main(String[] args) {
        int[] numbers = new int[]{4, 1, 2, 9, 10, 1, 2, -2, 4, -9, 1};
        int k = 4;
        int currentSum = 0;
        for (int idx = 0; idx < k; idx++) {
            currentSum += numbers[idx];
        }
        int maxSum = currentSum;
        for (int idx = k; idx < numbers.length; idx++) {
            currentSum -= numbers[idx - k];
            currentSum += numbers[idx];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        System.out.println(maxSum);
    }
}
