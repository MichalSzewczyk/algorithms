package com.algo.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

class CoinChange {

    /*
     * Complete the 'getWays' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. LONG_INTEGER_ARRAY c
     *
     * Given an amount and the denominations of coins available, determine how many ways change
     * can be made for amount. There is a limitless supply of each coin type.
     *
     * Sample input:
     * 10 4
     * 2 5 3 6
     *
     * Expected output:
     * 5
     */

    public static long getWays(int n, long[] values) {
        Arrays.sort(values);
        long[][] results = new long[n + 1][values.length];
        for (int nIdx = 0; nIdx < n + 1; nIdx++) {
            for (int valuesIdx = 0; valuesIdx < values.length; valuesIdx++) {
                results[nIdx][valuesIdx] = -1;
            }
        }
        getWaysRecursive(0, n, values, results);
        return results[n][0];
    }

    public static long getWaysRecursive(int idx, int n, long[] values, long[][] results) {
        if (results[n][idx] != -1) {
            return results[n][idx];
        } else {
            long result = 0;
            for (int currentIdx = idx; currentIdx < values.length; currentIdx++) {
                int currentN = n - (int) values[currentIdx];
                if (currentN == 0) {
                    result++;
                } else if (currentN > 0) {
                    result += getWaysRecursive(currentIdx, currentN, values, results);
                }
            }
            results[n][idx] = result;
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        long[] c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = CoinChange.getWays(n, c);

        System.out.println(ways);
        System.out.println();

        bufferedReader.close();
    }
}