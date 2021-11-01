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
