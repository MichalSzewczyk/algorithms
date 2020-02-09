package com.algo.absolutepermutation;

public class Algorithm {
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
