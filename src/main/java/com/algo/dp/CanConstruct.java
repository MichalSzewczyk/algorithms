package com.algo.dp;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class CanConstruct {
    public static void main(String[] args) {
        System.out.println(canConstruct("abcdefg", new String[]{"abcd", "cd", "efg"}));
    }

    private static boolean canConstruct(String word, String[] subWords) {
        char[] wordChars = word.toCharArray();
        Collection<char[]> subWordsChars = Arrays.stream(subWords).map(String::toCharArray).collect(Collectors.toList());
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            if (current == wordChars.length) {
                return true;
            } else if (current < wordChars.length) {
                for (char[] subWord : subWordsChars) {
                    boolean matches = true;
                    for (int index = 0; index < subWord.length; index++) {
                        if (current + index < wordChars.length && subWord[index] != wordChars[current + index]) {
                            matches = false;
                            break;
                        }
                    }
                    if (matches) {
                        queue.add(current + subWord.length);
                    }
                }
            }
        }
        return false;
    }
}
