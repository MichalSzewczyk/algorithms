package com.algo.magicsquare;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Algorithm {
    /**
     * You will be given a 3x3 matrix 9 of integers in the inclusive range [1, 9].
     * We can convert any digit a to any other digit b in the range [1, 9] at cost of |a - b|.
     * Given matrix "matrix", convert it into a magic square at minimal cost. Print this cost on a new line.
     * Complete the formingMagicSquare function in the editor below. It should return an integer that
     * represents the minimal total cost of converting the input square to a magic square.
     * formingMagicSquare has the following parameter(s):
     * matrix: 3x3 matrix of integers
     */
    static List<int[][]> allMagicSquares = generateAllMagicSquares(3);

    static int formingMagicSquare(int[][] matrix) {
        return allMagicSquares.stream().mapToInt(square -> transitionCost(square, matrix)).min().orElseThrow(IllegalAccessError::new);
    }

    static int transitionCost(int[][] firstMatrix, int[][] secondMatrix) {
        int transitionCost = 0;
        for (int index = 0; index < firstMatrix.length * firstMatrix.length; index++) {
            transitionCost += Math.abs(firstMatrix[index / firstMatrix.length][index % firstMatrix.length] - secondMatrix[index / firstMatrix.length][index % firstMatrix.length]);
        }
        return transitionCost;
    }

    static List<int[][]> generateAllMagicSquares(int columnSize) {
        CopyOnWriteArrayList<Integer> digits = new CopyOnWriteArrayList<>();
        for (int digit = 1; digit <= columnSize * columnSize; digit++) {
            digits.add(digit);
        }
        List<int[][]> allSquares = generateSquares(digits, new int[columnSize][columnSize]);
        return filterIfNotMagic(allSquares, 3, 15);
    }

    private static int[][] copySquareArray(int[][] array) {
        int[][] copy = new int[array.length][array.length];
        for (int i = 0; i < array.length * array.length; i++) {
            copy[i / array.length][i % array.length] = array[i / array.length][i % array.length];
        }
        return copy;
    }

    private static List<int[][]> generateSquares(CopyOnWriteArrayList<Integer> availableDigits, int[][] currentArray) {
        if (availableDigits.size() == 1) {
            List<int[][]> squares = new ArrayList<>();
            currentArray[currentArray.length - 1][currentArray.length - 1] = availableDigits.get(0);
            squares.add(copySquareArray(currentArray));
            return squares;
        }
        List<int[][]> squares = new ArrayList<>();
        for (int index = 0; index < availableDigits.size(); index++) {
            int currentDigit = availableDigits.remove(index);
            int currentPosition = currentArray.length * currentArray.length - availableDigits.size() - 1;
            currentArray[currentPosition / currentArray.length][currentPosition % currentArray.length] = currentDigit;
            List<int[][]> generatedSquares = generateSquares(availableDigits, currentArray);
            squares.addAll(generatedSquares);
            availableDigits.add(index, currentDigit);
        }

        return squares;
    }

    private static List<int[][]> filterIfNotMagic(List<int[][]> squares, int squareSize, int magicSum) {
        List<int[][]> magicSquares = new ArrayList<>();
        for (int[][] square : squares) {
            boolean isMagic = true;
            for (int rowIndex = 0; rowIndex < squareSize; rowIndex++) {
                int sum = 0;
                for (int colIndex = 0; colIndex < squareSize; colIndex++) {
                    sum += square[rowIndex][colIndex];
                }
                if (sum != magicSum) {
                    isMagic = false;
                    break;
                }
            }
            for (int colIndex = 0; colIndex < squareSize; colIndex++) {
                int sum = 0;
                for (int rowIndex = 0; rowIndex < squareSize; rowIndex++) {
                    sum += square[rowIndex][colIndex];
                }
                if (sum != magicSum) {
                    isMagic = false;
                    break;
                }
            }
            int sumFirst = 0;
            int sumSecond = 0;
            for (int index = 0; index < squareSize; index++) {
                sumFirst += square[index][squareSize - index - 1];
                sumSecond += square[index][index];
            }
            if (sumFirst != magicSum || sumSecond != magicSum) {
                isMagic = false;
            }
            if (isMagic) {
                magicSquares.add(square);
            }
        }
        return magicSquares;
    }
}
