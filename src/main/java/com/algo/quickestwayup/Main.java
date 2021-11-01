package com.algo.quickestwayup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    /**
     * Markov takes out his Snakes and Ladders game, stares at the board and wonders:
     * "If I can always roll the die to whatever number I want, what would be the
     * least number of rolls to reach the destination?"
     * <p>
     * Rules The game is played with a cubic die of 6 faces numbered 1 to 6.
     * - Starting from square 1, land on square 100 with the exact roll of the die.
     * If moving the number rolled would place the player beyond square 100, no move is made.
     * - If a player lands at the base of a ladder, the player must climb the ladder. Ladders go up only.
     * - If a player lands at the mouth of a snake,
     * the player must go down the snake and come out through the tail. Snakes go down only.
     * <p>
     * Sample Input:
     * 2
     * 3
     * 32 62
     * 42 68
     * 12 98
     * 7
     * 95 13
     * 97 25
     * 93 37
     * 79 27
     * 75 19
     * 49 47
     * 67 17
     * 4
     * 8 52
     * 6 80
     * 26 42
     * 2 72
     * 9
     * 51 19
     * 39 11
     * 37 29
     * 81 3
     * 59 5
     * 79 23
     * 53 7
     * 43 33
     * 77 21
     * <p>
     * Sample Output:
     * 3
     * 5
     */
    public static final int BOARD_SIZE = 100;

    public static int quickestWayUp(int[] ladders, int[] snakes) {
        List<int[]> nodes = new LinkedList<>();
        nodes.add(new int[]{0, 0});
        int[] costs = new int[BOARD_SIZE];
        while (!nodes.isEmpty()) {
            int[] currentNode = nodes.remove(0);
            int currentIdx = currentNode[0];
            int currentCost = currentNode[1];
            if (currentIdx == BOARD_SIZE - 1) {
                return currentCost;
            } else if (currentCost < costs[currentIdx] || costs[currentIdx] == 0) {
                costs[currentIdx] = currentCost;
                if (ladders[currentIdx] != 0) {
                    nodes.add(new int[]{ladders[currentIdx], currentCost});
                } else if (snakes[currentIdx] != -1) {
                    nodes.add(new int[]{snakes[currentIdx], currentCost});
                } else {
                    for (int idx = 1; idx <= 6; idx++) {
                        nodes.add(new int[]{currentIdx + idx, currentCost + 1});
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                int[] ladders = new int[100];

                IntStream.range(0, n).forEach(i -> {
                    try {
                        int[] currentLadder = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt).mapToInt(value -> value).toArray();
                        ladders[currentLadder[0] - 1] = currentLadder[1] - 1;
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int m = Integer.parseInt(bufferedReader.readLine().trim());
                int[] snakes = new int[100];
                Arrays.fill(snakes, -1);

                IntStream.range(0, m).forEach(i -> {
                    try {
                        int[] currentSnake =
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt).mapToInt(value -> value).toArray();
                        snakes[currentSnake[0] - 1] = currentSnake[1] - 1;
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int result = quickestWayUp(ladders, snakes);

                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
