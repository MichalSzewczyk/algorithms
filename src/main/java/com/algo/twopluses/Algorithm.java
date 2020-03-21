package com.algo.twopluses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Algorithm {
    // Complete the twoPluses function below.
    static int twoPluses(char[][] grid, int xSize, int ySize) {
        System.out.println(Arrays.deepToString(grid));
        System.out.println("xSize: " + xSize);
        System.out.println("ySize: " + ySize);
        int[][] leftTopPart = getHalfCrossesMatrix(grid, xSize, ySize, true);
        int[][] rightBottomPart = getHalfCrossesMatrix(grid, xSize, ySize, false);
        int[][] plusMatrix = new int[ySize][xSize];
        List<int[]> pointsWithCoordinates = new ArrayList<>();
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                int currentPlusSize = Math.min(leftTopPart[y][x], rightBottomPart[y][x]);
                if (currentPlusSize > 0) {
                    for(int i = 1; i <= currentPlusSize; i++) {
                        pointsWithCoordinates.add(new int[]{i, x, y});
                    }
                }
            }
        }
        pointsWithCoordinates.sort((firstArray, secondArray) -> secondArray[0] - firstArray[0]);
        int biggestSurfaceMultiplication = 0;
        for (int x = 0; x < pointsWithCoordinates.size(); x++) {
            for (int y = x + 1; y < pointsWithCoordinates.size(); y++) {

                if (!areOverlapping(pointsWithCoordinates.get(x), pointsWithCoordinates.get(y))) {
                    int firstSurface = computeSurface(pointsWithCoordinates.get(x)[0]);
                    int secondSurface = computeSurface(pointsWithCoordinates.get(y)[0]);
                    int multiplicationResult = firstSurface * secondSurface;
                    if (biggestSurfaceMultiplication < multiplicationResult) {
                        biggestSurfaceMultiplication = multiplicationResult;
                    }
                }
            }
        }
        pointsWithCoordinates.forEach(array -> System.out.println(Arrays.toString(array)));
        return biggestSurfaceMultiplication;
    }

    static boolean areOverlapping(int[] first, int[] second) {
        int xDistance = Math.abs(first[1] - second[1]);
        int yDistance = Math.abs(first[2] - second[2]);
        int minimumXDistance;
        int minimumYDistance;
        if (first[1] == second[1]) {
            minimumYDistance = first[0] + second[0] - 2;
        } else {
            minimumYDistance = Math.max(first[0], second[0]) - 1;
        }

        if (first[2] == second[2]) {
            minimumXDistance = first[0] + second[0] - 2;
        } else {
            minimumXDistance = Math.max(first[0], second[0]) - 1;
        }

        if (xDistance > minimumXDistance || yDistance > minimumYDistance) {
            return false;
        }
        return true;
    }

    static int computeSurface(int length) {
        return length * 4 - 3;
    }

    static int[][] getHalfCrossesMatrix(char[][] grid, int xSize, int ySize, boolean isTopDown) {
        int[] maximumCrosses = new int[xSize];
        int[][] leftTopPart = new int[ySize][xSize];
        if(ySize > 0) {
            for(int x = 0; x < grid[0].length; x++) {
                int xIndex = isTopDown ? x: grid[0].length - x - 1;
                int yIndex = isTopDown ? 0: grid.length - 1;
                if(isGood(grid[yIndex][xIndex])) {
                    maximumCrosses[xIndex]++;
                    leftTopPart[yIndex][xIndex]++;
                }
            }
        }
        for(int y = 1; y < grid.length; y++) {
            int currentLeftMax = 0;
            int yIndex = isTopDown ? y: grid.length - y - 1;
            for(int x = 0; x < grid[yIndex].length; x++) {
                int xIndex = isTopDown ? x: grid[yIndex].length - x - 1;
                if(isGood(grid[yIndex][xIndex])) {
                    currentLeftMax++;
                    maximumCrosses[xIndex]++;
                } else {
                    currentLeftMax = 0;
                    maximumCrosses[xIndex] = 0;
                }
                leftTopPart[yIndex][xIndex] = Math.min(currentLeftMax, maximumCrosses[xIndex]);
            }
        }
        return leftTopPart;
    }

    static boolean isGood(char character) {
        return character == 'G';
    }
}
