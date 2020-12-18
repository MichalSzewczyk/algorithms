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
    /**
     * Link: https://www.hackerrank.com/challenges/cut-the-tree/problem
     *
     * Anna loves graph theory! She has a tree where each vertex is numbered from  to , and each contains a data value.
     * The sum of a tree is the sum of all its nodes' data values. If she cuts an edge in her tree, she forms two smaller trees.
     * The difference between two trees is the absolute value between their sums.
     * Given a tree, determine which edge to cut so that the resulting trees have a minimal difference between them, then return that difference.
     * For example, your tree's nodes have weights of [1, 2, 3, 4, 5, 6]. In this case, node numbers match their weights for convenience. In the diagram below, you have the following edges:
     * [(1, 2), (1, 3), (2, 6), (3, 4), (3, 5)].
     *
     * The values are calculated as follows:
     *
     * Edge    Tree 1  Tree 2  Absolute
     * Cut     Sum      Sum     Difference
     * 1        8         13         5
     * 2        9         12         3
     * 3        6         15         9
     * 4        4         17        13
     * 5        5         16        11
     *
     *
     * Input Format
     * The first line contains an integer n, the number of vertices in the tree.
     * The second line contains n space-separated integers, where each integer u denotes the value of data[u].
     * Each of the n - 1 subsequent lines contains two space-separated integers u and v describing edge u < - > v in tree t.
     * Output Format
     * A single line containing the minimum difference possible for tree t.
     *
     * Sample Input
     *
     * 6
     * 100 200 100 500 100 600
     * 1 2
     * 2 3
     * 2 5
     * 4 5
     * 5 6
     * Sample Output
     *
     * 400
     */
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

