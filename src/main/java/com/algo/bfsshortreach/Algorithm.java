package com.algo.bfsshortreach;

import java.util.*;

public class Algorithm {
    /**
     *
     * Consider an undirected graph where each edge is the same weight. Each of the nodes is labeled consecutively.
     *
     * You will be given a number of queries. For each query, you will be given a list of edges describing an undirected graph.
     * After you create a representation of the graph, you must determine and report the shortest distance to each of
     * the other nodes from a given starting position using the breadth-first search algorithm (BFS).
     * Distances are to be reported in node number order, ascending. If a node is unreachable, print  for that node.
     * Each of the edges weighs 6 units of distance.
     *
     * Complete the bfs function in the editor below. It must return an array of integers representing distances
     * from the start node to each other node in node ascending order. If a node is unreachable, its distance is -1.
     *
     * bfs has the following parameter(s):
     * n: the integer number of nodes
     * m: the integer number of edges
     * edges: a 2D array of start and end nodes for edges
     * s: the node to start traversals from
     *
     * Sample Input
     * 2
     * 4 2
     * 1 2
     * 1 3
     * 1
     * 3 1
     * 2 3
     * 2
     *
     * Sample Output
     * 6 6 -1
     * -1 6
     */

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {
        int[] distances = new int[n];
        List<List<Integer>> adjacencyList = new ArrayList<>(n);
        for (int index = 0; index < n; index++) {
            adjacencyList.add(new ArrayList<>());
        }
        List<int[]> edgesToVisit = new LinkedList<>();
        Set<Integer> visitedNodes = new HashSet<>();
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        for (int node : adjacencyList.get(s)) {
            edgesToVisit.add(new int[]{s, node});
        }
        while (!edgesToVisit.isEmpty()) {
            int[] currentEdge = edgesToVisit.remove(0);
            if (!visitedNodes.contains(currentEdge[1])) {
                distances[currentEdge[1]] = distances[currentEdge[0]] + 6;
                visitedNodes.add(currentEdge[1]);
                for (int node : adjacencyList.get(currentEdge[1])) {
                    edgesToVisit.add(new int[]{currentEdge[1], node});
                }
            }
        }
        int[] distancesWithoutCurrent = new int[n - 1];
        for (int index = 0; index < n; index++) {
            if (index != s) {
                int indexWithoutCurrent = index < s ? index : index - 1;
                int distance = distances[index];
                if (distance == 0) {
                    distance = -1;
                }
                distancesWithoutCurrent[indexWithoutCurrent] = distance;
            }
        }
        return distancesWithoutCurrent;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem - 1;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s - 1);

            System.out.println(Arrays.toString(result));
        }

        scanner.close();
    }
}
