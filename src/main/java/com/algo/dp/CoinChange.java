package com.algo.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
     */

    public static long getWays(int n, long[] values) {
        Arrays.sort(values);
        return getWaysRecursive(0, 0, n, values);
    }

    public static long getWaysRecursive(int idx, long sum, long targetSum, long[] values) {
        long result = 0;
        for (int currentIdx = idx; currentIdx < values.length; currentIdx++) {
            long currentSum = sum + values[currentIdx];
            if (currentSum == targetSum) {
                result++;
            } else if (currentSum < targetSum) {
                result += getWaysRecursive(currentIdx, currentSum, targetSum, values);
            }
        }
        return result;
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