package com.algo.ranges;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Algorithm {
    /**
     * Link: https://www.hackerrank.com/challenges/connected-cell-in-a-grid/problem
     * Consider a matrix where each cell contains either a 0 or a 1. Any cell containing a 1 is called a filled cell.
     * Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally.
     * In the following grid, all cells marked X are connected to the cell marked Y.
     * XXX
     * XYX
     * XXX
     * If one or more filled cells are also connected, they form a region.
     * Note that each cell in a region is connected to zero or more cells in the region but is not necessarily
     * directly connected to all the other cells in the region.
     * Given an n x m matrix, find and print the number of cells in the largest region in the matrix.
     * Note that there may be more than one region in the matrix.
     * For example, there are two regions in the following 3 x 3 matrix. The larger region at the top left contains 3 cells.
     * The smaller one at the bottom right contains .
     * 110
     * 100
     * 001
     *
     * Sample Input:
     * 4
     * 4
     * 1 1 0 0
     * 0 1 1 0
     * 0 0 1 0
     * 1 0 0 0
     *
     * Sample Output:
     * 5
     */
    static int connectedCell(int[][] matrix) {
        List<int[]> nodesToVisit = new LinkedList<>();
        int maxDistanceWalked = 0;
        for (int xIndex = 0; xIndex < matrix.length; xIndex++) {
            for (int yIndex = 0; yIndex < matrix[xIndex].length; yIndex++) {
                if (matrix[xIndex][yIndex] == 1) {
                    nodesToVisit.add(new int[]{xIndex, yIndex});
                    int distanceWalked = walk(nodesToVisit, matrix);
                    maxDistanceWalked = Math.max(maxDistanceWalked, distanceWalked);
                }
            }
        }
        return maxDistanceWalked;
    }

    private static int walk(List<int[]> nodesToVisit, int[][] matrix) {
        int distance = 0;
        while (!nodesToVisit.isEmpty()) {
            int[] node = nodesToVisit.remove(0);
            if (isInMatrix(node, matrix) && isInRegion(node, matrix)) {
                distance++;
                matrix[node[0]][node[1]] = -1;
                nodesToVisit.add(new int[]{node[0] - 1, node[1] - 1});
                nodesToVisit.add(new int[]{node[0] - 1, node[1] + 1});
                nodesToVisit.add(new int[]{node[0] + 1, node[1] - 1});
                nodesToVisit.add(new int[]{node[0] + 1, node[1] + 1});
                nodesToVisit.add(new int[]{node[0] + 1, node[1]});
                nodesToVisit.add(new int[]{node[0] - 1, node[1]});
                nodesToVisit.add(new int[]{node[0], node[1] + 1});
                nodesToVisit.add(new int[]{node[0], node[1] - 1});
            }
        }
        return distance;
    }

    private static boolean isInRegion(int[] node, int[][] matrix) {
        return matrix[node[0]][node[1]] == 1;
    }

    private static boolean isInMatrix(int[] node, int[][] matrix) {
        return node[0] >= 0 && node[1] >= 0 && matrix.length > node[0] && matrix[0].length > node[1];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix);

        System.out.println(result);
        scanner.close();
    }
}
