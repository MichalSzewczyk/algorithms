package com.algo.containers;

import java.util.Arrays;

public class Solution {
    /**
     * David has several containers, each with a number of balls in it.
     * He has just enough containers to sort each type of ball he has into its own container.
     * David wants to sort the balls using his sort method.
     * In a single operation, David can swap two balls located in different containers.
     */
    static String organizingContainers(int[][] container) {
        int[] slotsInContainer = new int[container.length];
        int[] amountPerType = new int[container.length];
        for (int containerIndex = 0; containerIndex < container.length; containerIndex++) {
            for (int type = 0; type < container.length; type++) {
                slotsInContainer[containerIndex] += container[containerIndex][type];
                amountPerType[type] += container[containerIndex][type];
            }
        }
        Arrays.sort(slotsInContainer);
        Arrays.sort(amountPerType);
        if (Arrays.equals(slotsInContainer, amountPerType)) {
            return "Possible";
        }
        return "Impossible";
    }
}
