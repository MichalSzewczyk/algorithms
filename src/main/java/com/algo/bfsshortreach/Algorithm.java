package com.algo.bfsshortreach;

import java.util.*;

public class Algorithm {

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
                int indexWihotutCurrent = index < s ? index : index - 1;
                int distance = distances[index];
                if (distance == 0) {
                    distance = -1;
                }
                distancesWithoutCurrent[indexWihotutCurrent] = distance;
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
