package com.algo.dp;

import java.util.*;

/**
 *
 */
public class HowSumDp {
    public static void main(String[] args) {
        int[] result = howSum(100, new int[]{1, 2, 5, 25});
        System.out.println(Arrays.toString(result));
    }

    private static int[] howSum(int sum, int[] values) {
        Deque<Integer> sums = new LinkedList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        sums.add(sum);
        while (!sums.isEmpty()) {
            int currentSum = sums.removeFirst();
            if (currentSum == 0) {
                return getPath(visited, sum);
            }
            for (int value : values) {
                int next = currentSum - value;
                if (next >= 0 & !visited.containsKey(next)) {
                    visited.put(next, currentSum);
                    sums.add(next);
                }
            }
        }
        return new int[]{};
    }

    private static int[] getPath(Map<Integer, Integer> walkedSums, int sum) {
        int currentSum = 0;
        Collection<Integer> result = new LinkedList<>();
        while (currentSum != sum) {
            int nextSum = walkedSums.get(currentSum);
            result.add(nextSum - currentSum);
            currentSum = nextSum;
        }
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}
