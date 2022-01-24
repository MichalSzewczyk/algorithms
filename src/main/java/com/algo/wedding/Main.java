package com.algo.wedding;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int largestWedding = countLargestWedding(10, new int[][]{
                {1, 2},
                {2, 3},
                {1, 3},
        });
        System.out.println(largestWedding);
    }

    private static int countLargestWedding(int guests, int[][] mutuallyExclusivePairs) {
        boolean[][] guestsAdjacency = new boolean[guests][guests];
        int[] neighbourCount = new int[guests];
        Arrays.fill(neighbourCount, 1);
        for (int xIdx = 0; xIdx < mutuallyExclusivePairs.length; xIdx++) {
            int firstFromPair = mutuallyExclusivePairs[xIdx][0];
            int secondFromPair = mutuallyExclusivePairs[xIdx][1];
            neighbourCount[firstFromPair]++;
            neighbourCount[secondFromPair]++;
            guestsAdjacency[firstFromPair][secondFromPair] = true;
            guestsAdjacency[secondFromPair][firstFromPair] = true;
        }

        return getLargestClique(guestsAdjacency, neighbourCount);
    }

    private static int getLargestClique(boolean[][] guestsAdjacency, int[] neighbourCount) {
        int nodesWithNeighboursCount = countNeighbours(neighbourCount);
        int[] nodeWithLowestNeighboursCount = getPositiveMin(neighbourCount);
        while (nodesWithNeighboursCount != 0 && nodeWithLowestNeighboursCount[0] != nodesWithNeighboursCount) {
            removeNode(nodeWithLowestNeighboursCount[1], guestsAdjacency, neighbourCount);
            nodesWithNeighboursCount = countNeighbours(neighbourCount);
            nodeWithLowestNeighboursCount = getPositiveMin(neighbourCount);
        }
        return nodesWithNeighboursCount;
    }

    private static void removeNode(int nodeIdx, boolean[][] guestsAdjacency, int[] neighbourCount) {
        neighbourCount[nodeIdx] = 0;
        Arrays.fill(guestsAdjacency[nodeIdx], false);
        for (int idx = 0; idx < guestsAdjacency.length; idx++) {
            if (guestsAdjacency[idx][nodeIdx]) {
                guestsAdjacency[idx][nodeIdx] = false;
                neighbourCount[idx]--;
            }
        }
    }

    private static int[] getPositiveMin(int[] neighbourCount) {
        int min = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int idx = 0; idx < neighbourCount.length; idx++) {
            int current = neighbourCount[idx];
            if (min > current && current > 0) {
                min = current;
                minIdx = idx;
            }
        }
        if (min == Integer.MAX_VALUE) {
            min = 0;
        }
        return new int[]{min, minIdx};
    }

    private static int countNeighbours(int[] neighbourCount) {
        int nodesWithNeighboursCount = 0;
        for (int count : neighbourCount) {
            if (count != 0) {
                nodesWithNeighboursCount++;
            }
        }
        return nodesWithNeighboursCount;
    }
}
