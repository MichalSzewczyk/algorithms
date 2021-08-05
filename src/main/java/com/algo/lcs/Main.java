package com.algo.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;


public class Main {
    /**
     * Hacker rank link:
     * https://www.hackerrank.com/challenges/substring-diff/problem
     * <p>
     * Problem description:
     * Complete the substringDiff function in the editor below.
     * It should return an integer that represents the length of the longest
     * common substring as defined.
     * substringDiff has the following parameter(s):
     * - k: an integer that represents the maximum number of differing characters in a matching pair
     * - s1: the first string
     * - s2: the second string
     *
     * For each test case, output a single integer which is the length
     * of the maximum length common substrings differing at k or fewer positions.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int k = Integer.parseInt(firstMultipleInput[0]);

                String s1 = firstMultipleInput[1];

                String s2 = firstMultipleInput[2];

                int result = substringDiff(k, s1, s2);

                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    public static int substringDiff(int k, String s1, String s2) {
        char[] first = s1.toCharArray();
        char[] second = s2.toCharArray();
        int firstMaxLength = getMaxLength(first, second, k);
        int secondMaxLength = getMaxLength(second, first, k);
        return Math.max(firstMaxLength, secondMaxLength);
    }

    public static int getMaxLength(char[] first, char[] second, int k) {
        int maxLength = 0;
        for (int diff = 0; diff < first.length; diff++) {
            int currentMaxLength = getMaxLengthForDiff(first, second, diff, k);
            maxLength = Math.max(maxLength, currentMaxLength);
        }
        return maxLength;
    }

    public static int getMaxLengthForDiff(char[] first, char[] second, int diff, int k) {
        int maxLength = 0;
        int start = 0;
        int end = 0;
        int currentMisses = 0;
        while (end + diff < first.length && end < second.length) {
            if (first[end + diff] != second[end]) {
                currentMisses++;
            }
            while (currentMisses > k) {
                if (first[start + diff] != second[start]) {
                    currentMisses--;
                }
                start++;
            }
            maxLength = Math.max(end - start + 1, maxLength);
            end++;
        }
        return maxLength;
    }
}
