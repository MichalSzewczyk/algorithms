package com.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PathConstruct {
    public static void main(String[] args) {
        List<List<char[]>> result = construct("abbbc", new String[]{"ab", "bbc", "abbbc", "a", "b", "c"});
        for (List<char[]> chars : result) {
            for (char[] currChars : chars) {
                System.out.print(Arrays.toString(currChars) + " ");
            }
            System.out.println();
        }
    }

    private static List<List<char[]>> construct(String word, String[] subWords) {
        char[][] subWordsChars = new char[subWords.length][];
        for (int index = 0; index < subWords.length; index++) {
            subWordsChars[index] = subWords[index].toCharArray();
        }
        List<List<List<char[]>>> memo = new ArrayList<>();
        for (int index = 0; index < word.length(); index++) {
            memo.add(new LinkedList<>());
        }
        construct(0, word.toCharArray(), subWordsChars, memo, new boolean[word.length()]);
        return memo.get(0);
    }

    private static void construct(int index, char[] word, char[][] subWords, List<List<List<char[]>>> memo, boolean[] visited) {
        if (!visited[index] && index < word.length) {
            for (char[] subWord : subWords) {
                int nextIdx = index + subWord.length;
                if (nextIdx <= word.length) {
                    boolean matches = true;
                    for (int currentIdx = 0; currentIdx < subWord.length; currentIdx++) {
                        if (word[index + currentIdx] != subWord[currentIdx]) {
                            matches = false;
                        }
                    }
                    if (matches) {
                        if (nextIdx == word.length) {
                            List<char[]> chars = new LinkedList<>();
                            chars.add(subWord);
                            memo.get(index).add(chars);
                        } else {
                            construct(nextIdx, word, subWords, memo, visited);
                            for (List<char[]> paths : memo.get(nextIdx)) {
                                List<char[]> chars = new LinkedList<>(paths);
                                chars.add(0, subWord);
                                memo.get(index).add(chars);
                            }
                        }
                    }
                }
            }
            visited[index] = true;
        }
    }
}
