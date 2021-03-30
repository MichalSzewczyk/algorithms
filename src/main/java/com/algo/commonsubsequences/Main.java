package com.algo.commonsubsequences;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] b = new int[m];

        String[] bItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int bItem = Integer.parseInt(bItems[i]);
            b[i] = bItem;
        }

        int[] result = longestCommonSubsequence(a, b);

        System.out.println(Arrays.toString(result));

        scanner.close();
    }

    private static int[] longestCommonSubsequence(int[] a, int[] b) {
        int[][] sums = new int[a.length][b.length];
        sums[0][0] = a[0] == b[0] ? 1 : 0;
        for (int index = 1; index < a.length; index++) {
            sums[index][0] = a[index] == b[0] ? 1 : sums[index - 1][0];
        }
        for (int index = 1; index < b.length; index++) {
            sums[0][index] = a[0] == b[index] ? 1 : sums[0][index - 1];
        }
        for (int aIndex = 1; aIndex < a.length; aIndex++) {
            for (int bIndex = 1; bIndex < b.length; bIndex++) {
                if (a[aIndex] == b[bIndex]) {
                    sums[aIndex][bIndex] = sums[aIndex - 1][bIndex - 1] + 1;
                } else {
                    sums[aIndex][bIndex] = Math.max(sums[aIndex - 1][bIndex], sums[aIndex][bIndex - 1]);
                }
            }
        }

        return determineSequence(sums, a, b);
    }

    private static int[] determineSequence(int[][] sums, int[] a, int[] b) {
        int[] result = new int[sums[a.length - 1][b.length - 1]];
        int index = result.length - 1;
        int aIndex = a.length - 1;
        int bIndex = b.length - 1;
        while (index > 0) {
            if (a[aIndex] == b[bIndex]) {
                result[index--] = a[aIndex];
                aIndex--;
                bIndex--;
            } else {
                if (sums[aIndex - 1][bIndex] > sums[aIndex][bIndex - 1]) {
                    aIndex--;
                } else {
                    bIndex--;
                }
            }
        }
        result[index] = aIndex == 0 ? a[aIndex] : b[bIndex];
        return result;
    }
}
