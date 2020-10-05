package com.algo.ranges;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Algorithm {
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
