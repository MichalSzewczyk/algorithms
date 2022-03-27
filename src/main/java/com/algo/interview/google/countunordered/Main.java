package com.algo.interview.google.countunordered;

/**
 * Given the unordered list of integers.
 * Return number of integers for which the binary search algorithm applied on the input list will give the correct result.
 * Example 1:
 * Input: 1, 2, 3, 4
 * Outbut: 4
 * <p>
 * Example 2:
 * Input: 1, 3, 2, 4
 * Output: 2
 * <p>
 * Example 3:
 * Input: 4, 3, 2, 1
 * Output: 0
 */
public class Main {
    public static void main(String[] args) {
        int unordered = countUnordered(new int[]{1, 3, 2});
        System.out.println(unordered);
    }

    private static int countUnordered(int[] ints) {
        int maxValue = ints[0];
        int unordered = 0;
        boolean[] counted = new boolean[ints.length];
        for (int idx = 1; idx < ints.length; idx++) {
            if (maxValue > ints[idx]) {
                unordered++;
                counted[idx] = true;
            } else {
                maxValue = ints[idx];
            }
        }
        int minValue = ints[ints.length - 1];
        for (int idx = ints.length - 2; idx >= 0; idx--) {
            if (ints[idx] > minValue && !counted[idx]) {
                unordered++;
            } else {
                minValue = ints[idx];
            }
        }
        return unordered;
    }
}
