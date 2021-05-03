package com.algo.dp;

public class CountMoves {
    public static void main(String[] args) {
        int moves = countMoves(3, 3);
        System.out.println(moves);
    }

    private static int countMoves(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] arr = new int[m][n];
        arr[0][0] = 1;
        for (int idx = 1; idx < m; idx++) {
            arr[idx][0] = arr[idx - 1][0];
        }
        for (int idx = 1; idx < n; idx++) {
            arr[0][idx] = arr[0][idx - 1];
        }
        for (int mIdx = 1; mIdx < m; mIdx++) {
            for (int nIdx = 1; nIdx < n; nIdx++) {
                arr[mIdx][nIdx] = arr[mIdx - 1][nIdx] + arr[mIdx][nIdx - 1];
            }
        }
        return arr[m - 1][n - 1];
    }
}
