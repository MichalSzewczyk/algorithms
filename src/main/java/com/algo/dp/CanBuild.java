package com.algo.dp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class CanBuild {
    public static void main(String[] args) {
        boolean result = canBuild("aadsa", new String[]{"asd", "dsa", "aa"});
        System.out.println(result);
    }

    private static boolean canBuild(String word, String[] subWords) {
        char[] chars = word.toCharArray();
        char[][] subChars = new char[subWords.length][];
        for (int index = 0; index < subWords.length; index++) {
            subChars[index] = subWords[index].toCharArray();
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.add(0);
        while (!toVisit.isEmpty()) {
            int next = toVisit.remove();
            if (next == chars.length) {
                return true;
            } else if (next < chars.length) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    for (char[] sub : subChars) {
                        if (next + sub.length <= chars.length) {
                            boolean matches = true;
                            for (int index = 0; index < sub.length; index++) {
                                if (sub[index] != chars[next + index]) {
                                    matches = false;
                                    break;
                                }
                            }
                            if (matches) {
                                toVisit.add(next + sub.length);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
