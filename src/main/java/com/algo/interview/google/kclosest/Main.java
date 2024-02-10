package com.algo.interview.google.kclosest;

import java.util.ArrayList;
import java.util.List;

public class Main {
     /*
     https://leetcode.com/problems/find-k-closest-elements/
      */

    public static void main(String[] args) {
        int[] values = new int[]{0, 1, 2, 2, 2, 3, 6, 8, 8, 9};

        List<Integer> closest = findClosestElements(values, 5, 9);
        System.out.println(closest);

    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int closestIndex = findClosestIndex(arr, x, k);
        int start = Math.max(0, closestIndex - k - 2);
        int minIdx = start;
        int currentDistance = 0;
        for (int idx = minIdx; idx < minIdx + k; idx++) {
            currentDistance += Math.abs(x - arr[idx]);
        }
        int minDistance = currentDistance;
        for (int startIdx = start + 1; startIdx < Math.min(closestIndex + k + 2, arr.length - k + 1); startIdx++) {
            currentDistance = currentDistance - Math.abs(x - arr[startIdx - 1]) + Math.abs(x - arr[startIdx + k - 1]);
            if (minDistance > currentDistance) {
                minDistance = currentDistance;
                minIdx = startIdx;
            }
        }
        List<Integer> result = new ArrayList<>(k);
        for (int idx = minIdx; idx < minIdx + k; idx++) {
            result.add(arr[idx]);
        }
        return result;
    }

    private static int findClosestIndex(int[] arr, int x, int k) {
        int startIdx = k;
        int endIdx = arr.length - k;
        int currentIdx = arr.length / 2;
        while (arr[currentIdx] != x && startIdx < endIdx - 1) {
            if (x < arr[currentIdx]) {
                endIdx = currentIdx;
            } else {
                startIdx = currentIdx;
            }
            currentIdx = (startIdx + endIdx) / 2;
        }
        return currentIdx;
    }
}
