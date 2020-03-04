package com.algo.bomberman;

import java.util.Arrays;

public class Main {
    static char[][] bomberMan(int n, char[][] grid) {
        if(n % 2 == 0) {
            return fillWithBombs(grid);
        }
        n = n % 12;

        for(int i = 2 ; i <= n ; i += 2) {
            grid = reverse(grid);
        }
        return grid;
    }

    static char[][] reverse(char[][] grid) {
        char[][] newGrid = new char[grid.length][grid[0].length];
        newGrid = fillWithBombs(newGrid);
        for(int i = 0 ; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 'O') {
                    if(i > 0) {
                        newGrid[i - 1][j] = '.';
                    }
                    if(j > 0) {
                        newGrid[i][j - 1] = '.';
                    }
                    if(i < grid.length - 1) {
                        newGrid[i + 1][j] = '.';
                    }
                    if(j < grid[i].length - 1) {
                        newGrid[i][j + 1] = '.';
                    }
                    newGrid[i][j] = '.';
                }
            }
        }
        return newGrid;
    }

    static char[][] fillWithBombs(char[][] grid) {
        for(char[] row : grid) {
            Arrays.fill(row, 'O');
        }
        return grid;
    }
}
