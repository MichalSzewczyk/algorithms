package com.algo.codeforces.contests.notshading;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://codeforces.com/contest/1627/problem/A
 * There is a grid with n rows and m columns. Some cells are colored black, and the rest of the cells are colored white.
 * <p>
 * In one operation, you can select some black cell and do exactly one of the following:
 * <p>
 * color all cells in its row black, or
 * color all cells in its column black.
 * You are given two integers r and c. Find the minimum number of operations required to make the cell in row r and column c black, or determine that it is impossible.
 * <p>
 * Input
 * The input consists of multiple test cases. The first line contains an integer t (1≤t≤100) — the number of test cases. The description of the test cases follows.
 * <p>
 * The first line of each test case contains four integers n, m, r, and c (1≤n,m≤50; 1≤r≤n; 1≤c≤m) — the number of rows and the number of columns in the grid, and the row and column of the cell you need to turn black, respectively.
 * <p>
 * Then n lines follow, each containing m characters. Each of these characters is either 'B' or 'W' — a black and a white cell, respectively.
 * <p>
 * Output
 * For each test case, if it is impossible to make the cell in row r and column c black, output −1.
 * <p>
 * Otherwise, output a single integer — the minimum number of operations required to make the cell in row r and column c black.
 * <p>
 * Input:
 * 9
 * 3 5 1 4
 * WBWWW
 * BBBWB
 * WWBBB
 * 4 3 2 1
 * BWW
 * BBW
 * WBB
 * WWB
 * 2 3 2 2
 * WWW
 * WWW
 * 2 2 1 1
 * WW
 * WB
 * 5 9 5 9
 * WWWWWWWWW
 * WBWBWBBBW
 * WBBBWWBWW
 * WBWBWBBBW
 * WWWWWWWWW
 * 1 1 1 1
 * B
 * 1 1 1 1
 * W
 * 1 2 1 1
 * WB
 * 2 1 1 1
 * W
 * B
 * <p>
 * Output:
 * 1
 * 0
 * -1
 * 2
 * 2
 * 0
 * -1
 * 1
 * 1
 */

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int noTestCases = scanner.nextInt();
            while (noTestCases-- > 0) {
                solveTestCase(scanner);
            }
        }
    }

    private static void solveTestCase(Scanner scanner) {
        int rowsNumber = scanner.nextInt();
        int colsNumber = scanner.nextInt();
        int targetRow = scanner.nextInt() - 1;
        int targetCol = scanner.nextInt() - 1;
        scanner.nextLine();
        char[][] rows = new char[rowsNumber][];
        for (int idx = 0; idx < rowsNumber; idx++) {
            rows[idx] = scanner.nextLine().toCharArray();
        }
        if (rows[targetRow][targetCol] == 'B') {
            System.out.println(0);
        } else {
            boolean blackInRow = isBlackInRow(rows, targetRow);
            boolean blackInCol = isBlackInCol(rows, targetCol);
            if (blackInRow && blackInCol) {
                System.out.println(1);
            } else if (isBlack(rows)) {
                System.out.println(2);
            } else {
                System.out.println(-1);
            }
        }
    }

    private static boolean isBlack(char[][] rows) {
        for (char[] row : rows) {
            for (char c : row) {
                if (c == 'B') {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isBlackInCol(char[][] rows, int targetCol) {
        for (char[] row : rows) {
            if (row[targetCol] == 'B') {
                return true;
            }
        }
        return false;

    }

    private static boolean isBlackInRow(char[][] rows, int targetRow) {
        for (char c : rows[targetRow]) {
            if (c == 'B') {
                return true;
            }
        }
        return false;
    }
}
