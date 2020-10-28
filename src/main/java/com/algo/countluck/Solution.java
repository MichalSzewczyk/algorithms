package com.algo.countluck;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static String countLuck(char[][] matrix, int startX, int startY, int k) {
        List<int[]> decisionPoints = new LinkedList<>();
        int decisions = 0;
        int currentX = startX;
        int currentY = startY;
        while(matrix[currentX][currentY] != '*') {
            List<int[]> nextPoints = getNextPoints(matrix, currentX, currentY);
            int[] point;
            if(nextPoints.size() > 1) {
                if(matrix[currentX][currentY] != 'v') {
                    decisions++;
                }
                matrix[currentX][currentY] = 'v';
                decisionPoints.add(new int[]{currentX, currentY, decisions});
                point = nextPoints.get(0);
            } else if (nextPoints.size() == 1) {
                matrix[currentX][currentY] = 'v';
                point = nextPoints.get(0);
            } else {
                matrix[currentX][currentY] = 'v';
                point = decisionPoints.remove(decisionPoints.size() - 1);
                decisions = point[2];
            }
            currentX = point[0];
            currentY = point[1];
        }
        if(decisions == k) {
            return "Impressed";
        } else {
            return "Oops!";
        }
    }
    private static List<int[]> getNextPoints(char[][] matrix, int currentX, int currentY) {
        List<int[]> points = new LinkedList<>();
        if(currentX - 1 >= 0 && (matrix[currentX - 1][currentY] == '.' || matrix[currentX - 1][currentY] == '*')) {
            points.add(new int[]{currentX - 1, currentY});
        }
        if(currentX + 1 < matrix.length && (matrix[currentX + 1][currentY] == '.' || matrix[currentX + 1][currentY] == '*')) {
            points.add(new int[]{currentX + 1, currentY});
        }
        if(currentY - 1 >= 0 && (matrix[currentX][currentY - 1] == '.' || matrix[currentX][currentY - 1] == '*')) {
            points.add(new int[]{currentX, currentY - 1});
        }
        if(currentY + 1 < matrix[0].length && (matrix[currentX][currentY + 1] == '.' || matrix[currentX][currentY + 1] == '*')) {
            points.add(new int[]{currentX, currentY + 1});
        }
        return points;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            char[][] matrix = new char[n][];
            int startX = -1;
            int startY = -1;
            for (int xIndex = 0; xIndex < n; xIndex++) {
                char[] matrixItem = scanner.nextLine().toCharArray();
                matrix[xIndex] = matrixItem;
                for (int yIndex = 0; yIndex < matrixItem.length; yIndex++) {
                    if (matrix[xIndex][yIndex] == 'M') {
                        startX = xIndex;
                        startY = yIndex;
                    }
                }
            }

            int k = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = countLuck(matrix, startX, startY, k);

            System.out.println(result);
        }

        scanner.close();
    }
}
