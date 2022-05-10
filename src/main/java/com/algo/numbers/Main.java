package com.algo.numbers;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] reversed = reverse(new int[]{1, 2, 4, 6});
        System.out.println(Arrays.toString(reversed));
    }

    private static int[] reverse(int[] ints) {
        for (int idx = 0; idx < ints.length / 2; idx++) {
            ints[idx] += ints[ints.length - 1 - idx];
            ints[ints.length - 1 - idx] = ints[idx] - ints[ints.length - 1 - idx];
            ints[idx] -= ints[ints.length - 1 - idx];
        }
        return ints;
    }
}
