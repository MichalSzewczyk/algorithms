package com.algo.clique;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] neighbours = {
                {0, 1},
                {0, 2},
                {1, 2},
                {1, 3},

        };
        int size = getMaximumClique(neighbours, 4);
        System.out.println(size);
    }

    private static int getMaximumClique(int[][] neighbours, int numberOfNodes) {
        int[] numberOfNeighbours = new int[numberOfNodes];

        for (int[] pair : neighbours) {
            numberOfNeighbours[pair[0]]++;
            numberOfNeighbours[pair[1]]++;
        }
        boolean[][] adjacencyMatrix = getAdjacencyMatrix(neighbours, numberOfNodes);

        int[] next = getNodeWithLowestNeighbours(numberOfNeighbours);
        while (numberOfNodes != next[1] + 1) {
            numberOfNodes--;
            int currentIdx = next[0];
            numberOfNeighbours[currentIdx] = -1;
            for (int x = 0; x < adjacencyMatrix.length; x++) {
                if (adjacencyMatrix[x][currentIdx]) {
                    numberOfNeighbours[x]--;
                }
            }
            next = getNodeWithLowestNeighbours(numberOfNeighbours);
        }
        return numberOfNodes;
    }

    private static boolean[][] getAdjacencyMatrix(int[][] pairs, int numberOfNodes) {
        boolean[][] adjacencyMatrix = new boolean[numberOfNodes][numberOfNodes];
        for (int[] pair : pairs) {
            adjacencyMatrix[pair[0]][pair[1]] = true;
            adjacencyMatrix[pair[1]][pair[0]] = true;
        }
        return adjacencyMatrix;
    }

    private static int[] getNodeWithLowestNeighbours(int[] values) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int idx = 0; idx < values.length; idx++) {
            if (min > values[idx] && values[idx] != -1) {
                min = values[idx];
                index = idx;
            }

        }
        return new int[]{index, min};
    }
}
