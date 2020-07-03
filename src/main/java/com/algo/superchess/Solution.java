package com.algo.superchess;

import java.util.*;

public class Solution {
    /**
     * In ordinary chess, the pieces are only of two colors, black and white. In our version of chess, we are including new pieces with unique movements. One of the most powerful pieces in this version is the red knight.
     * The red knight can move to six different positions based on its current position (UpperLeft, UpperRight, Right, LowerRight, LowerLeft, Left)
     * <p>
     * The board is a grid of size n x n. Each cell is identified with a pair of coordinates (i, j), where i is the row number and j is the column number, both zero-indexed. Thus, (0, 0) is the upper-left corner and (n - 1, n - 1) is the bottom-right corner.
     * Complete the function printShortestPath, which takes as input the grid size n, and the coordinates of the starting and ending position (iStart, jStart) and (iEnd, jEnd) respectively, as input. The function does not return anything.
     * Given the coordinates of the starting position of the red knight and the coordinates of the destination, print the minimum number of moves that the red knight has to make in order to reach the destination and after that, print the order of
     * the moves that must be followed to reach the destination in the shortest way. If the destination cannot be reached, print only the word "Impossible".
     * <p>
     * Note: There may be multiple shortest paths leading to the destination. Hence, assume that the red knight considers its possible neighbor locations in the following order of priority: UL, UR, R, LR, LL, L.
     * In other words, if there are multiple possible options, the red knight prioritizes the first move in this list, as long as the shortest path is still achievable.
     * <p>
     * Sample Input:
     * 7
     * 0 3 4 3
     * <p>
     * Sample Output:
     * 2
     * LR LL
     */

    private static final String[] PRIORITIZED_MOVES = {"UL", "UR", "R", "LR", "LL", "L"};
    private static final Map<String, int[]> MOVE_LITERALS_TO_COORDINATES = initializeMovesToCoordinates();

    private static Map<String, int[]> initializeMovesToCoordinates() {
        Map<String, int[]> moves = new HashMap<>();
        moves.put("UL", new int[]{-1, -2});
        moves.put("UR", new int[]{1, -2});
        moves.put("L", new int[]{-2, 0});
        moves.put("R", new int[]{2, 0});
        moves.put("LL", new int[]{-1, 2});
        moves.put("LR", new int[]{1, 2});
        return moves;
    }

    // Complete the printShortestPath function below.
    static void printShortestPath(int n, int jStart, int iStart, int jEnd, int iEnd) {
        int[][] previous = new int[n][n];
        LinkedList<int[]> stack = new LinkedList<>();
        stack.add(new int[]{iStart, jStart});
        previous[iStart][jStart] = -1;
        while (!stack.isEmpty()) {
            int[] currentNode = stack.remove();
            if (currentNode[0] == iEnd && currentNode[1] == jEnd) {
                System.out.println(getListOfSteps(previous, currentNode[0], currentNode[1]));
                return;
            } else {
                for (int index = 0; index < PRIORITIZED_MOVES.length; index++) {
                    int nextX = currentNode[0] + MOVE_LITERALS_TO_COORDINATES.get(PRIORITIZED_MOVES[index])[0];
                    int nextY = currentNode[1] + MOVE_LITERALS_TO_COORDINATES.get(PRIORITIZED_MOVES[index])[1];
                    if (nextX >= 0 && nextX < n
                            && nextY >= 0 && nextY < n) {
                        if (previous[nextX][nextY] == 0) {
                            previous[nextX][nextY] = index + 1;
                            stack.add(new int[]{nextX, nextY});
                        }
                    }
                }
            }
        }
        System.out.println("Impossible");
    }

    static String getListOfSteps(int[][] previousNodes, int currentX, int currentY) {
        int numberOfMoves = 0;
        StringBuilder builder = new StringBuilder();
        while (previousNodes[currentX][currentY] != -1) {
            numberOfMoves++;
            String move = PRIORITIZED_MOVES[previousNodes[currentX][currentY] - 1];
            builder.insert(0, move);
            builder.insert(0, " ");
            int[] current = MOVE_LITERALS_TO_COORDINATES.get(move);
            currentX = currentX - current[0];
            currentY = currentY - current[1];
        }
        builder.insert(0, numberOfMoves + "\n");
        return builder.toString();
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String[] i_startJ_start = scanner.nextLine().split(" ");
        int i_start = Integer.parseInt(i_startJ_start[0]);
        int j_start = Integer.parseInt(i_startJ_start[1]);
        int i_end = Integer.parseInt(i_startJ_start[2]);
        int j_end = Integer.parseInt(i_startJ_start[3]);
        printShortestPath(n, i_start, j_start, i_end, j_end);
        scanner.close();
    }
}
