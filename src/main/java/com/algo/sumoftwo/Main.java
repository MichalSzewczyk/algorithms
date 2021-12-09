package com.algo.sumoftwo;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(sumOfTwo(new int[]{1, 3, 4, 2}, 10));
    }

    static boolean sumOfTwo(int[] ints, int value) {
        Set<Integer> set = new HashSet<>();
        for (int i : ints) {
            set.add(i);
        }
        for (int i : set) {
            if (set.contains(value - i)) {
                return true;
            }
        }
        return false;
    }

}
