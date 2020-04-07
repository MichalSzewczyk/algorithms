package com.algo.larrysarray;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Algorithm {
    static String larrysArray(int[] array) {
        NavigableSet<Integer> numbersTree = new TreeSet<>();
        int swaps = 0;
        for (int index = 0; index < array.length; index++) {
            int currentNumber = array[index];
            swaps += numbersTree.tailSet(currentNumber).size();
            numbersTree.add(currentNumber);
        }
        if (swaps % 2 == 1) {
            return "NO";
        }
        return "YES";
    }
}
