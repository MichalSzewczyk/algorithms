package com.algo.evenforest;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
    static int evenForest(IntList[] edges) {
        int removedEdges = 0;
        List<Integer> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(0);
        while (!nodesToVisit.isEmpty()) {
            int currentNode = nodesToVisit.remove(0);
            for (Integer node : edges[currentNode]) {
                nodesToVisit.add(node);
                int children = countChildren(node, edges);
                if ((children + 1) % 2 == 0) {
                    removedEdges++;
                }
            }
        }
        return removedEdges;
    }

    static int countChildren(int node, IntList[] edges) {
        List<Integer> nodesToVisit = new LinkedList<>(edges[node]);
        int counter = 0;
        while (!nodesToVisit.isEmpty()) {
            System.out.println(nodesToVisit);
            int current = nodesToVisit.remove(0);
            counter++;
            nodesToVisit.addAll(edges[current]);
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int nodes = Integer.parseInt(tNodesEdges[0]);
        int tEdges = Integer.parseInt(tNodesEdges[1]);

        IntList[] edges = new IntList[nodes];
        for (int index = 0; index < nodes; index++) {
            edges[index] = new IntList();
        }
        IntStream.range(0, tEdges).forEach(i -> {
            try {
                String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                int from = Integer.parseInt(tFromTo[0]) - 1;
                int to = Integer.parseInt(tFromTo[1]) - 1;
                if (from > to) {
                    from += to;
                    to = from - to;
                    from = from - to;
                }
                edges[from].add(to);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = evenForest(edges);

        System.out.println(res);
        bufferedReader.close();
    }

    static class IntList extends ArrayList<Integer> {

    }
}
