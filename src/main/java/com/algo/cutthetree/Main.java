package com.algo.cutthetree;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int[] data = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        boolean[][] neighbours = new boolean[n][n];

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                int[] edge = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                neighbours[edge[0] - 1][edge[1] - 1] = true;
                neighbours[edge[1] - 1][edge[0] - 1] = true;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = cutTheTree(data, neighbours);
        System.out.println(result);
        bufferedReader.close();
    }

    public static int cutTheTree(int[] data, boolean[][] adjacencyList) {
        List<Integer> cuttingQueue = new LinkedList<>();
        List<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int currentNode = queue.remove(0);
            cuttingQueue.add(0, currentNode);
            for (int index = 0; index < adjacencyList.length; index++) {
                if (adjacencyList[currentNode][index]) {
                    adjacencyList[index][currentNode] = false;
                    queue.add(index);
                }
            }
        }
        int[] costs = new int[adjacencyList.length];
        while (!cuttingQueue.isEmpty()) {
            int current = cuttingQueue.remove(0);
            for (int index = 0; index < adjacencyList.length; index++) {
                if (adjacencyList[current][index]) {
                    costs[current] += costs[index] + data[index];
                }
            }
        }
        int sum = 0;
        for (int index = 0; index < adjacencyList.length; index++) {
            sum += data[index];
        }
        int minDiff = Integer.MAX_VALUE;
        System.out.println(sum);
        for (int index = 0; index < adjacencyList.length; index++) {
            costs[index] += data[index];
            int currentDiff = Math.abs(sum - 2 * costs[index]);
            minDiff = Math.min(currentDiff, minDiff);
        }
        System.out.println(Arrays.toString(costs));
        return minDiff;
    }
}
