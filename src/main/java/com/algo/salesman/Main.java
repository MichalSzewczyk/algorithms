package com.algo.salesman;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] adjacencyMatrix = new int[][]{
                new int[]{-1, 1, 2, -1},
                new int[]{1, -1, 1, -1},
                new int[]{-1, -1, -1, 1},
                new int[]{1, 2, -1, -1},
        };

        int result = findShortestHamiltonCycle(0, adjacencyMatrix);
        System.out.println(result);
    }

    private static int findShortestHamiltonCycle(int firstNode, int[][] adjacencyMatrix) {
        Step first = new Step(firstNode, new boolean[adjacencyMatrix.length], adjacencyMatrix, 0);
        Queue<Step> steps = new LinkedList<>();
        steps.add(first);
        int cost = -1;
        while(!steps.isEmpty()) {
            Step current = steps.poll();
            if(current.current == firstNode && current.allVisited() && (cost > current.cost || cost == -1)) {
                cost = current.cost;
            }
            Collection<Step> toAdd = new LinkedList<>();
            for (Step step: current.next()) {
                if (step.cost < cost || cost == -1) {
                    toAdd.add(step);
                }
            }
            steps.addAll(toAdd);
        }
        return cost;
    }

    static class Step implements Cloneable {
        private final int current;
        private final boolean[] visited;
        private final int[][] adjacencyMatrix;
        private final int cost;

        public Step(int current, boolean[] visited, int[][] adjacencyMatrix, int cost) {
            this.current = current;
            this.visited = visited;
            this.adjacencyMatrix = adjacencyMatrix;
            this.cost = cost;
        }

        Collection<Step> next() {
            int[] currentMatrix = adjacencyMatrix[current];
            Collection<Step> steps = new LinkedList<>();
            for (int idx = 0; idx < currentMatrix.length; idx++) {
                if(currentMatrix[idx] != -1) {
                    boolean[] newVisited = visited.clone();
                    newVisited[idx] = true;
                    steps.add(new Step(idx, newVisited, adjacencyMatrix, cost + currentMatrix[idx]));
                }
            }
            return steps;
        }

        public boolean allVisited() {
            for(boolean node: visited) {
                if(!node) {
                    return false;
                }
            }
            return true;
        }
    }
}
