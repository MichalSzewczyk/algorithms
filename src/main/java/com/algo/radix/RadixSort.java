package com.algo.radix;

import java.util.Arrays;


public class RadixSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 99, 999, 1};
        numbers = sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static int[] sort(int[] numbers) {
        int[] result = new int[numbers.length];
        int max = Integer.MIN_VALUE;
        for (int number : numbers) {
            max = Math.max(number, max);
        }
        int maxDigits = digits(max);
        for (int digit = 0; digit < maxDigits; digit++) {
            int mod = (int) Math.pow(10, digit);
            int[] buckets = new int[10];
            for (int idx = 0; idx < numbers.length; idx++) {
                int bucket = (numbers[idx] % (mod * 10)) / mod;
                buckets[bucket]++;
            }
            for (int idx = 1; idx < buckets.length; idx++) {
                buckets[idx] = buckets[idx] + buckets[idx - 1];
            }
            for (int idx = result.length - 1; idx >= 0; idx--) {
                int i = buckets[(numbers[idx] % (mod * 10)) / mod]-- - 1;
                result[i] = numbers[idx];
            }
            numbers = result.clone();
        }
        return result;
    }

    private static int digits(int max) {
        int digits = 0;
        while (max >= Math.pow(10, digits)) {
            digits++;
        }
        return digits;
    }
}
