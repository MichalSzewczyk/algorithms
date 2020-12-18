package com.algo.cutthetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

        IntList[] neighbours = new IntList[data.length];
        for (int index = 0; index < neighbours.length; index++) {
            neighbours[index] = new IntList();
        }

        IntStream.range(0, n - 1).forEach(i -> {

            try {
                int[] edge = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                neighbours[edge[0] - 1].add(edge[1] - 1);
                neighbours[edge[1] - 1].add(edge[0] - 1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        int sum = 0;
        for (int index = 0; index < neighbours.length; index++) {
            sum += data[index];
        }
        int result = cutTheTree(data, sum, neighbours);
        System.out.println(result);
        bufferedReader.close();
    }

    public static int cutTheTree(int[] data, int sum, IntList[] neighbours) {
        List<Integer> cuttingQueue = new LinkedList<>();
        List<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int currentNode = queue.remove(0);
            cuttingQueue.add(0, currentNode);
            for (int current : neighbours[currentNode]) {
                neighbours[current].remove((Integer) currentNode);
                queue.add(current);
            }
        }
        while (!cuttingQueue.isEmpty()) {
            int current = cuttingQueue.remove(0);
            for (int neighbour : neighbours[current]) {
                data[current] += data[neighbour];
            }
        }
        int minDiff = Integer.MAX_VALUE;
        for (int index = 0; index < neighbours.length; index++) {
            int currentDiff = Math.abs(sum - 2 * data[index]);
            minDiff = Math.min(currentDiff, minDiff);
        }
        return minDiff;
    }

    private static class IntList extends ArrayList<Integer> {
    }
}

