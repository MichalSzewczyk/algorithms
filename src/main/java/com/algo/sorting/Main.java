package com.algo.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * In this challenge you need to print the string that accompanies each integer in a list sorted by the integers.
     * If two strings are associated with the same integer, they must be printed in their original order so your sorting algorithm should be stable.
     * There is one other twist. The first half of the strings encountered in the inputs are to be replaced with the character "-".
     * Insertion Sort and the simple version of Quicksort are stable, but the faster in-place version of Quicksort is not since it scrambles around elements while sorting.
     *
     * Sample input:
     * 20
     * 0 ab
     * 6 cd
     * 0 ef
     * 6 gh
     * 4 ij
     * 0 ab
     * 6 cd
     * 0 ef
     * 6 gh
     * 0 ij
     * 4 that
     * 3 be
     * 0 to
     * 1 be
     * 5 question
     * 1 or
     * 2 not
     * 4 is
     * 2 to
     * 4 the
     *
     * Sample output:
     * - - - - - to be or not to be - that is the question - - - -
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        int indexOfMiddleElement = n / 2;
        StringBuilder[] buckets = new StringBuilder[n];

        for (int index = 0; index < buckets.length; index++) {
            String[] entry = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            if(index < indexOfMiddleElement) {
                entry[1] = "-";
            }
            int currentIndex = Integer.parseInt(entry[0]);
            if(buckets[currentIndex] == null) {
                buckets[currentIndex] = new StringBuilder();
            } else {
                buckets[currentIndex].append(" ");
            }
            buckets[currentIndex].append(entry[1]);
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder bucket : buckets) {
            if (bucket != null) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append(bucket);
            }
        }
        System.out.println(result);
        bufferedReader.close();
    }
}
