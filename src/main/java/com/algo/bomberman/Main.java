package com.algo.bomberman;

import java.util.Arrays;

public class Main {
    /**
     * Each bomb can be planted in any cell of the grid but once planted,
     * it will detonate after exactly 3 seconds. Once a bomb detonates,
     * it's destroyed — along with anything in its four neighboring cells.
     * This means that if a bomb detonates in cell (i, j), any valid cells (i +/- 1, j) and (i, j +/- 1) are cleared. If there is a bomb in a neighboring cell, the neighboring bomb is destroyed without detonating, so there's no chain reaction.
     *
     * Bomberman is immune to bombs, so he can move freely throughout the grid. Here's what he does:
     *
     * Initially, Bomberman arbitrarily plants bombs in some of the cells, the initial state.
     * 1. After one second, Bomberman does nothing.
     * 2. After one more second, Bomberman plants bombs in all cells without bombs, thus filling the whole grid with bombs. No bombs detonate at this point.
     * 3. After one more second, any bombs planted exactly three seconds ago will detonate. Here, Bomberman stands back and observes.
     * 4. Bomberman then repeats steps 3 and 4 indefinitely.
     */
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
