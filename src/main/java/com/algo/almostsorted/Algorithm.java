package com.algo.almostsorted;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    /**
     * Given an array of integers, determine whether the array can
     * be sorted in ascending order using only one of the following operations one time.
     * - Swap two elements.
     * - Reverse one sub-segment.
     * Determine whether one, both or neither of the operations will complete the task.
     * If both work, choose swap. For instance, given an array [2, 3, 5, 4] either swap the 4 and 5, or reverse them to sort the array.
     * Choose swap. The Output Format section below details requirements.
     *
     * 1. If the array is already sorted, output yes on the first line. You do not need to output anything else.
     *
     * 2. If you can sort this array using one single operation (from the two permitted operations) then output yes on the first line and then:
     * a. If elements can be swapped, d[l] and d[r], output swap l r in the second line. l and r are the indices of the elements to be swapped,
     * assuming that the array is indexed from 1 to n.
     * b. Otherwise, when reversing the segment d[l ... r], output reverse l r in the second line. l and r are the indices of the first
     * and last elements of the subsequence to be reversed, assuming that the array is indexed from 1 to n.
     * d[l ... r] represents the sub-sequence of the array, beginning at index l and ending at index r, both inclusive.
     * If an array can be sorted by either swapping or reversing, choose swap.
     *
     * 3. If you cannot sort the array either way, output no on the first line.
     *
     */
    static void almostSorted(int[] arr) {
        List<int[]> descendingSequencesSizes = new ArrayList<>();
        boolean wasPreviousDescending = false;
        int descendingSequenceSize = 0;
        for (int index = 1; index < arr.length; index++) {
            if (arr[index - 1] > arr[index]) {
                wasPreviousDescending = true;
                descendingSequenceSize++;
            } else {
                if (wasPreviousDescending) {
                    descendingSequencesSizes.add(new int[]{descendingSequenceSize, index});
                }
                descendingSequenceSize = 0;
                wasPreviousDescending = false;
            }
        }
        if (wasPreviousDescending) {
            descendingSequencesSizes.add(new int[]{descendingSequenceSize, arr.length});
        }

        if (descendingSequencesSizes.size() == 1 && (descendingSequencesSizes.get(0)[0] == 2 || descendingSequencesSizes.get(0)[0] == 1)) {
            if (descendingSequencesSizes.get(0)[1] < arr.length && (arr[descendingSequencesSizes.get(0)[1]] < arr[descendingSequencesSizes.get(0)[1] - descendingSequencesSizes.get(0)[0] - 1] || arr[descendingSequencesSizes.get(0)[1]] - 1 > arr[descendingSequencesSizes.get(0)[1] - descendingSequencesSizes.get(0)[0] - 1])) {
                System.out.println("no");
            } else {
                System.out.println("yes");
                System.out.println("swap " + (descendingSequencesSizes.get(0)[1] - descendingSequencesSizes.get(0)[0] + " " + descendingSequencesSizes.get(0)[1]));
            }
        } else if (descendingSequencesSizes.size() == 1) {
            if (descendingSequencesSizes.get(0)[0] > 2) {
                System.out.println("yes");
                System.out.println("reverse " + (descendingSequencesSizes.get(0)[1] - descendingSequencesSizes.get(0)[0] + " " + descendingSequencesSizes.get(0)[1]));
            } else {
                System.out.println("no");
            }
        } else if (descendingSequencesSizes.size() == 2) {
            if (descendingSequencesSizes.get(0)[0] > 1 || descendingSequencesSizes.get(1)[0] > 1) {
                System.out.println("no");
            } else {
                System.out.println("yes");
                System.out.println("swap " + (descendingSequencesSizes.get(0)[1] - 1) + " " + descendingSequencesSizes.get(1)[1]);
            }
        } else if (descendingSequencesSizes.size() == 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
