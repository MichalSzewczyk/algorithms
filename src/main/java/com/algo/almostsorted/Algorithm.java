package com.algo.almostsorted;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
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
