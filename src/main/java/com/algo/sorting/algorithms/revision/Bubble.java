package com.algo.sorting.algorithms.revision;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int[] values = new int[]{5, 3, 6, 4, 1};
        sort(values);
        System.out.println(Arrays.toString(values));
    }

    private static void sort(int[] values) {
        boolean swapped = true;
        while(swapped){
            swapped = false;
            for (int idx = 1; idx < values.length; idx++) {
                if(values[idx - 1] > values[idx]) {
                    swapped = true;
                    int tmp = values[idx];
                    values[idx] = values[idx - 1];
                    values[idx - 1] = tmp;
                }
            }
        }
    }
}
