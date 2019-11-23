package com.algo.chess;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Algorithm {
    /**
     * You will be given a square chess board with one queen and a number of obstacles placed on it. Determine how many squares the queen can attack.
     * A queen is standing on an n x n chessboard. The chess board's rows are numbered from 1 to n, going from bottom to top.
     * Its columns are numbered from 1 to n, going from left to right. Each square is referenced by a tuple, (r, c), describing the row, r, and column, c, where the square is located.
     * The queen is standing at position (rq, cq). In a single move, she can attack any square in any of the eight directions (left, right, up, down, and the four diagonals).
     * In the diagram below, the green circles denote all the cells the queen can attack from (4, 4):
     */
    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        int maxTopDistance = n - r_q;
        int maxTopRightDistance = n - Math.max(r_q, c_q);
        int maxRightDistance = n - c_q;
        int maxRightDownDistance = Math.min(r_q - 1, n - c_q);
        int maxDownDistance = r_q - 1;
        int maxDownLeftDistance = Math.min(r_q - 1, c_q - 1);
        int maxLeftDistance = c_q - 1;
        int maxLeftTopDistance = Math.min(n - r_q, c_q - 1);
        for (int[] obstacle : obstacles) {
            int r = obstacle[0];
            int c = obstacle[1];

            if (c == c_q && r_q < r) {
                maxTopDistance = Math.min(maxTopDistance, r - r_q - 1);
            }
            if (c > c_q && r_q < r && c - c_q == r - r_q) {
                maxTopRightDistance = Math.min(maxTopRightDistance, Math.min(c - c_q - 1, r - r_q - 1));
            }
            if (r == r_q && c_q < c) {
                maxRightDistance = Math.min(maxRightDistance, c - c_q - 1);
            }
            if (c > c_q && r_q > r && c - c_q == r_q - r) {
                maxRightDownDistance = Math.min(maxRightDownDistance, Math.min(r_q - r - 1, c - c_q - 1));
            }
            if (c == c_q && r_q > r) {
                maxDownDistance = Math.min(maxDownDistance, r_q - r - 1);
            }
            if (c < c_q && r_q > r && c_q - c == r_q - r) {
                maxDownLeftDistance = Math.min(maxDownLeftDistance, Math.min(c_q - c - 1, r_q - r - 1));
            }
            if (c < c_q && r == r_q) {
                maxLeftDistance = Math.min(maxLeftDistance, c_q - c - 1);
            }
            if (c < c_q && r > r_q && c_q - c == r - r_q) {
                maxLeftTopDistance = Math.min(maxLeftTopDistance, Math.min(c_q - c - 1, r - r_q - 1));
            }
        }
        return maxTopDistance + maxTopRightDistance +
                maxRightDistance + maxRightDownDistance +
                maxDownDistance + maxDownLeftDistance +
                maxLeftDistance + maxLeftTopDistance;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
