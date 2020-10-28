package com.algo.countluck;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    /**
     * Link: https://www.hackerrank.com/challenges/count-luck/problem
     * Ron and Hermione are deep in the Forbidden Forest collecting potion ingredients, and they've managed to lose their way.
     * The path out of the forest is blocked, so they must make their way to a portkey that will transport them back to Hogwarts.
     * Consider the forest as an N x M grid. Each cell is either empty (represented by .) or blocked by a tree (represented by X).
     * Ron and Hermione can move (together inside a single cell) LEFT, RIGHT, UP, and DOWN through empty cells,
     * but they cannot travel through a tree cell. Their starting cell is marked with the character M, and the cell with
     * the portkey is marked with a *. The upper-left corner is indexed as (0, 0).
     * .X.X......X
     * .X*.X.XXX.X
     * .XX.X.XM...
     * ......XXXX.
     * In example above, Ron and Hermione are located at index (2, 7) and the portkey is at (1, 2).
     * Each cell is indexed according to Matrix Conventions.
     * Hermione decides it's time to find the portkey and leave. They start along the path and each time they have to choose a direction,
     * she waves her wand and it points to the correct direction. Ron is betting that she will have to wave her wand exactly k times.
     * Can you determine if Ron's guesses are correct?
     * <p>
     * The map from above has been redrawn with the path indicated as a series where M is the starting point (no decision in this case),
     * 1 indicates a decision point and 0 is just a step on the path:
     * .X.X.10000X
     * .X*0X0XXX0X
     * .XX0X0XM01.
     * ...100XXXX.
     * There are three instances marked with  where Hermione must use her wand.
     *
     * Sample Input:
     * 3
     * 2 3
     * *.M
     * .X.
     * 1
     * 4 11
     * .X.X......X
     * .X*.X.XXX.X
     * .XX.X.XM...
     * ......XXXX.
     * 3
     * 4 11
     * .X.X......X
     * .X*.X.XXX.X
     * .XX.X.XM...
     * ......XXXX.
     * 4
     *
     * Sample Output:
     * Impressed
     * Impressed
     * Oops!
     *
     */
    static String countLuck(char[][] matrix, int startX, int startY, int k) {
        List<int[]> decisionPoints = new LinkedList<>();
        int decisions = 0;
        int currentX = startX;
        int currentY = startY;
        while (matrix[currentX][currentY] != '*') {
            List<int[]> nextPoints = getNextPoints(matrix, currentX, currentY);
            int[] point;
            if (nextPoints.size() > 1) {
                if (matrix[currentX][currentY] != 'v') {
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
        if (decisions == k) {
            return "Impressed";
        } else {
            return "Oops!";
        }
    }

    private static List<int[]> getNextPoints(char[][] matrix, int currentX, int currentY) {
        List<int[]> points = new LinkedList<>();
        if (currentX - 1 >= 0 && (matrix[currentX - 1][currentY] == '.' || matrix[currentX - 1][currentY] == '*')) {
            points.add(new int[]{currentX - 1, currentY});
        }
        if (currentX + 1 < matrix.length && (matrix[currentX + 1][currentY] == '.' || matrix[currentX + 1][currentY] == '*')) {
            points.add(new int[]{currentX + 1, currentY});
        }
        if (currentY - 1 >= 0 && (matrix[currentX][currentY - 1] == '.' || matrix[currentX][currentY - 1] == '*')) {
            points.add(new int[]{currentX, currentY - 1});
        }
        if (currentY + 1 < matrix[0].length && (matrix[currentX][currentY + 1] == '.' || matrix[currentX][currentY + 1] == '*')) {
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
