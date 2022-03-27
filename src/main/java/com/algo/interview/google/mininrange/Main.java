package com.algo.interview.google.mininrange;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] values = {0, 1, 2, 3, 4, 5, 6, -7, -8};
        Algorithm algo = new Algorithm(values, 2);
        int minimumInRange = algo.getMinimumInRange(1, 8);
        System.out.println(minimumInRange);
    }

}

class Algorithm {
    private final int[] memo;
    private final int[] values;
    private final int memoStep;

    Algorithm(int[] values, int memoStep) {
        this.memo = computeMemo(values, memoStep);
        this.values = values;
        this.memoStep = memoStep;
    }

    public int getMinimumInRange(int start, int end) {
        int currentIdx = start;
        int min = Integer.MAX_VALUE;
        while (currentIdx != end) {
            if (currentIdx % memoStep == 0 && currentIdx + memoStep <= end) {
                min = Math.min(min, memo[currentIdx / memoStep]);
                currentIdx += memoStep;
            } else {
                min = Math.min(min, values[currentIdx]);
                currentIdx++;
            }
        }
        return min;
    }

    private int[] computeMemo(int[] values, int memoStep) {
        int[] memo = new int[values.length / memoStep];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[0] = values[0];
        for (int idx = 1; idx < values.length - values.length % memoStep; idx++) {
            if (idx % memoStep == 0) {
                memo[idx / memoStep] = values[idx];
            } else {
                memo[idx / memoStep] = Math.min(memo[idx / memoStep], values[idx]);
            }
        }
        return memo;
    }
}