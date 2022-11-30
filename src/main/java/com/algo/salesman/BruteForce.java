package com.algo.salesman;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteForce {
    public static void main(String[] args) {
        int[][] graph = new int[6][6];
        graph[0][1] = 3;
        graph[1][2] = 4;
        graph[2][3] = 5;
        graph[0][3] = 6;
        graph[3][0] = 7;
        int shortestPath = calculateShortestHamiltonCycle(graph);
        System.out.println(shortestPath);
    }

    private static int calculateShortestHamiltonCycle(int[][] graph) {
        List<int[]> combinations = new LinkedList<>();
        List<Integer> toVisit = new LinkedList<>();
        for (int idx = 0; idx < graph.length; idx++) {
            toVisit.add(idx);
        }
        List<int[]> allCombinations = getAllCombinations(0, new int[graph.length], toVisit, graph.length, combinations);
        allCombinations.forEach(arr -> System.out.println(Arrays.toString(arr)));
        System.out.println(allCombinations.size());
        return 0;
    }

    private static List<int[]> getAllCombinations(int idx, int[] current, List<Integer> toVisit, int length, List<int[]> combinations) {
        if (idx == length) {
            combinations.add(current.clone());
        } else {
            for (int nodeIdx = 0; nodeIdx < toVisit.size(); nodeIdx++) {
                Integer node = toVisit.remove(nodeIdx);
                current[idx] = node;
                getAllCombinations(idx + 1, current, toVisit, length, combinations);
                toVisit.add(nodeIdx, node);
            }
        }
        return combinations;
    }
}
