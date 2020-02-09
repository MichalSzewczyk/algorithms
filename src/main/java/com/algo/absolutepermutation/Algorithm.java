package com.algo.absolutepermutation;

public class Algorithm {
    /**
     * We define P to be a permutation of the first n natural numbers in the range [1, n].
     * Let pos[i] denote the value at position i in permutation P using 1-based indexing.
     * P is considered to be an absolute permutation if |pos[i] - i| = k holds true for every i in [1, n].
     * Given n and k, print the lexicographically smallest absolute permutation P.
     * If no absolute permutation exists, print -1.
     */
    static int[] absolutePermutation(int n, int k) {
        int[] result = new int[n];
        boolean[] isUsed = new boolean[n + 1];
        for (int index = 1; index <= n; index++) {
            int firstSolution = index - k;
            int secondSolution = index + k;
            int smallerSolution;
            int biggerSolution;
            if (firstSolution < secondSolution) {
                smallerSolution = firstSolution;
                biggerSolution = secondSolution;
            } else {
                smallerSolution = secondSolution;
                biggerSolution = firstSolution;
            }
            if (smallerSolution <= 0 || isUsed[smallerSolution]) {
                if (biggerSolution > n) {
                    return new int[]{-1};
                }
                isUsed[biggerSolution] = true;
                result[index - 1] = biggerSolution;
            } else {
                isUsed[smallerSolution] = true;
                result[index - 1] = smallerSolution;
            }
        }

        return result;
    }
}
