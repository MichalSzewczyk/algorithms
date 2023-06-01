package com.algo.sorting.algorithms.revision;

import java.util.Arrays;


public class Insertion {
    public static void main(String[] args) {
        int[] values = new int[]{3, 5, 4, 6, 1};
        sort(values);
        System.out.println(Arrays.toString(values));
    }

    private static void sort(int[] values) {
        if(values.length > 1) {
            for (int idx = 1; idx < values.length; idx++) {
                int current = idx;
                while(current > 0 && values[current - 1] > values[current]) {
                    int tmp = values[current - 1];
                    values[current - 1] = values[current];
                    values[current] = tmp;
                    current--;
                }
            }
        }
    }
}
