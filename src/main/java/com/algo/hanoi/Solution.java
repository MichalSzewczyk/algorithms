package com.algo.hanoi;

import java.util.*;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[N];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < N; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem - 1;
        }
        int cost = solve(a);
        System.out.println(cost);
        scanner.close();
    }
    static class Vector {
        private final int[] vector;

        public Vector(int[] vector) {
            this.vector = vector;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vector vector1 = (Vector) o;
            return Arrays.equals(vector, vector1.vector);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(vector);
        }
    }
    static int solve(int[] arr) {
        int[] targetVector = new int[arr.length];
        Arrays.fill(targetVector, 0);
        Set<Vector> visitedVectors = new HashSet<>();
        Queue<Node> nodesToVisit = new LinkedList<>();
        Node startNode = new Node(arr, 0);
        visitedVectors.add(new Vector(arr));
        nodesToVisit.add(startNode);
        while(!nodesToVisit.isEmpty()){
            Node currentNode = nodesToVisit.remove();
            if(currentNode.isTarget(targetVector)){
                return currentNode.getDistanceFromSource();
            } else {
                nodesToVisit.addAll(currentNode.getNextNodes(visitedVectors));
            }
        }
        return 0;
    }

    static class Node {
        private final int distanceFromSource;
        private final int[] vector;
        public Node(int[] vector, int distanceFromSource) {
            this.vector = vector;
            this.distanceFromSource = distanceFromSource;
        }

        boolean isTarget(int[] targetVector) {
            return Arrays.equals(vector, targetVector);
        }

        Collection<Node> getNextNodes(Collection<Vector> visited) {
            int distance = distanceFromSource + 1;
            int[] topRings = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
            for(int index = 0; index < vector.length; index++) {
                topRings[vector[index]] = Math.min(topRings[vector[index]], index);
            }
            Collection<Node> neightbours = new ArrayList<>();
            for(int currentTop = 0; currentTop < topRings.length; currentTop++) {
                for(int top = 0; top < topRings.length; top++) {
                    if(top != currentTop && topRings[currentTop] != Integer.MAX_VALUE) {
                        if(topRings[top] == Integer.MAX_VALUE) {
                            int[] newVector = Arrays.copyOf(vector, vector.length);
                            newVector[topRings[currentTop]] = top;
                            if(!visited.contains(new Vector(newVector))) {
                                neightbours.add(new Node(newVector, distance));
                                visited.add(new Vector(newVector));
                            }

                        } else if(topRings[currentTop] < topRings[top]) {
                            int[] newVector = Arrays.copyOf(vector, vector.length);
                            newVector[topRings[currentTop]] = top;
                            if(!visited.contains(new Vector(newVector))) {
                                neightbours.add(new Node(newVector, distance));
                                visited.add(new Vector(newVector));
                            }
                        }
                    }
                }
            }
            return neightbours;
        }
        int getDistanceFromSource() {
            return distanceFromSource;
        }
    }
}