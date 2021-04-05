package com.algo.dp;


/**
 * Given two integers: m, n
 * Calculate number of paths from (0, 0) to (m - 1, n - 1)
 * with only two moves possible: one step right, one step down
 */
public class NumberOfPathsDp {
    public static void main(String[] args) {
        System.out.println(countPaths(10, 5));
    }

    private static int countPaths(int m, int n) {
        int[][] counts = new int[m][n];
        if (m <= 1 || n <= 1) {
            return 1;
        }
        for (int index = 0; index < m; index++) {
            counts[index][0] = 1;
        }
        for (int index = 1; index < n; index++) {
            counts[0][index] = 1;
        }
        for (int mIndex = 1; mIndex < m; mIndex++) {
            for (int nIndex = 1; nIndex < n; nIndex++) {
                counts[mIndex][nIndex] = counts[mIndex][nIndex - 1] + counts[mIndex - 1][nIndex];
            }
        }
        return counts[m - 1][n - 1];
    }
}
