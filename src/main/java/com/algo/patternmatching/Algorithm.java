package com.algo.patternmatching;

public class Algorithm {
    /**
     * Given a 2D array of digits or grid, try to find the occurrence of a given 2D pattern of digits.
     * For example, consider the following grid:
     * 1234567890
     * 0987654321
     * 1111111111
     * 1111111111
     * 2222222222
     *
     * Assume we need to look for the following 2D pattern array:
     * 876543
     * 111111
     * 111111
     */
    static String gridSearch(char[][] grid, int gridRows, int gridCols,
                             char[][] pattern, int patternRows, int patternCols) {
        for (int colIndex = 0; colIndex <= gridCols - patternCols; colIndex++) {
            for (int rowIndex = 0; rowIndex <= gridRows - patternRows; rowIndex++) {
                boolean isMatching = isPatternMatching(rowIndex, colIndex,
                        grid, patternRows, patternCols, pattern);
                if (isMatching) {
                    return "YES";
                }
            }
        }
        return "NO";
    }

    static boolean isPatternMatching(int rowIndex, int colIndex,
                                     char[][] grid, int patternRows, int patternCols, char[][] pattern) {
        for (int currentRowIndex = 0; currentRowIndex < patternRows;
             currentRowIndex++) {
            for (int currentColIndex = 0; currentColIndex < patternCols;
                 currentColIndex++) {
                if (grid[rowIndex + currentRowIndex][colIndex + currentColIndex] !=
                        pattern[currentRowIndex][currentColIndex]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{1, 2, 3}, {2, 3, 4}};
        char[][] pattern = new char[][]{{3, 4}};
        System.out.println(gridSearch(grid, 2, 3, pattern, 1, 2));

    }
}
