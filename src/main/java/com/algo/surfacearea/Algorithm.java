package com.algo.surfacearea;

public class Algorithm {

    static int surfaceArea(int[][] array, int rows, int cols) {
        int sumOfExternalSurface = sumExternalSurface(array, rows, cols);
        int sumOfInternalSurface = sumInternalSurface(array, rows, cols);
        return sumOfExternalSurface + sumOfInternalSurface + 2 * rows * cols;
    }

    static int sumExternalSurface(int[][] array, int rows, int cols) {
        int externalSurfaceSum = 0;
        for (int index = 0; index < cols; index++) {
            externalSurfaceSum += array[0][index];
        }

        for (int index = 0; index < rows; index++) {
            externalSurfaceSum += array[index][0];
        }

        for (int index = 0; index < cols; index++) {
            externalSurfaceSum += array[rows - 1][index];
        }

        for (int index = 0; index < rows; index++) {
            externalSurfaceSum += array[index][cols - 1];
        }
        return externalSurfaceSum;
    }

    static int sumInternalSurface(int[][] array, int rows, int cols) {
        int internalSurfaceSum = 0;

        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int colIndex = 0; colIndex < cols - 1; colIndex++) {
                internalSurfaceSum += Math.abs(array[rowIndex][colIndex] -
                        array[rowIndex][colIndex + 1]);
            }
        }
        for (int rowIndex = 0; rowIndex < rows - 1; rowIndex++) {
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                internalSurfaceSum += Math.abs(array[rowIndex][colIndex] -
                        array[rowIndex + 1][colIndex]);
            }
        }
        return internalSurfaceSum;
    }
}
