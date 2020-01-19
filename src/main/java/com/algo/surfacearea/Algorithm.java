package com.algo.surfacearea;

public class Algorithm {
    /**
     * Madison, is a little girl who is fond of toys. Her friend Mason works in a toy manufacturing factory.
     * Mason has a 2D board A of size H x W with X rows and W columns. The board is divided into cells of size 1 x 1
     * with each cell indicated by it's coordinate (i, j). The cell (i, j) has an integer A[i, j] written on it.
     * To create the toy Mason stacks A[i, j] number of cubes of size 1 x 1 x 1 on the cell (i, j).
     * <p>
     * Given the description of the board showing the values of A[i, j] and that the price of the toy is
     * equal to the 3d surface area find the price of the toy.
     */

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
