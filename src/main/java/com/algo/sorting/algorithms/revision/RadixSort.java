package com.algo.sorting.algorithms.revision;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{50, 31, 200, 1,};
        numbers = sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static int[] sort(int[] numbers) {
        int maxNumber = Integer.MIN_VALUE;
        for (int idx = 0; idx < numbers.length; idx++) {
            maxNumber = Math.max(maxNumber, numbers[idx]);
        }
        int digits = getDigits(maxNumber);
        for (int digit = 0; digit < digits; digit++) {
            int[] buckets = new int[10];
            int[] result = new int[numbers.length];
            for (int number : numbers) {
                int currentDigit = getCurrentDigit(digit, number);
                buckets[currentDigit]++;
            }
            for (int idx = 1; idx < buckets.length; idx++) {
                buckets[idx] = buckets[idx] + buckets[idx - 1];
            }
            for (int idx = numbers.length - 1; idx >= 0; idx--) {
                int currentNumber = numbers[idx];
                int currentDigit = getCurrentDigit(digit, currentNumber);
                result[buckets[currentDigit]-- - 1] = currentNumber;
            }
            numbers = result.clone();
        }
        return numbers;
    }

    private static int getCurrentDigit(int digit, int number) {
        return (int) (((number % Math.pow(10, digit + 1)) / (Math.pow(10, digit))));
    }

    private static int getDigits(int number) {
        int digits = 1;
        while (Math.pow(10, digits) <= number) {
            digits++;
        }
        return digits;
    }
}
