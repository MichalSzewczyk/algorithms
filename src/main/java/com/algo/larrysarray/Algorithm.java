package com.algo.larrysarray;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Algorithm {
    /**
     * Larry has been given a permutation of a sequence of natural numbers incrementing from 1 as an array.
     * He must determine whether the array can be sorted using the following operation any number of times:
     * Choose any 3 consecutive indices and rotate their elements in such a way that ABC -> BCA -> CAB -> ABC.
     */
    static String larrysArray(int[] array) {
        NavigableSet<Integer> numbersTree = new TreeSet<>();
        int swaps = 0;
        for (int currentNumber : array) {
            swaps += numbersTree.tailSet(currentNumber).size();
            numbersTree.add(currentNumber);
        }
        if (swaps % 2 == 1) {
            return "NO";
        }
        return "YES";
    }
}
