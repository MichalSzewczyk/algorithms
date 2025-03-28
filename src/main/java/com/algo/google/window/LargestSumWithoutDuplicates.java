package com.algo.google.window;

import java.util.HashMap;
import java.util.Map;

public class LargestSumWithoutDuplicates {
    /*
    https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
     */
    public long maximumSubarraySum(int[] nums, int k) {
        long currentSum = 0;
        long maxSum = 0;
        Map<Integer, Integer> numsToIndexes = new HashMap<>(10000);
        int currentIdx = 0;
        while (currentIdx < nums.length) {
            if (numsToIndexes.containsKey(nums[currentIdx])) {
                int duplicateIdx = numsToIndexes.get(nums[currentIdx]);
                for (int idxToRemove = currentIdx - numsToIndexes.size(); idxToRemove <= duplicateIdx; idxToRemove++) {
                    currentSum -= nums[idxToRemove];
                    numsToIndexes.remove(nums[idxToRemove]);
                }
            }
            if (numsToIndexes.size() == k) {
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
                numsToIndexes.remove(nums[currentIdx - k]);
                currentSum -= nums[currentIdx - k];
            }
            numsToIndexes.put(nums[currentIdx], currentIdx);
            currentSum += nums[currentIdx];
            currentIdx++;
            if (numsToIndexes.size() == k && currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }
}
