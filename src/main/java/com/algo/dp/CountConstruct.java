package com.algo.dp;

import java.util.Arrays;

public class CountConstruct {
    public static void main(String[] args) {
        int result = countConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"});
        System.out.println(result);
    }

    private static int countConstruct(String word, String[] subWords) {
        char[] chars = word.toCharArray();
        char[][] subWordsChars = new char[subWords.length][];
        for (int index = 0; index < subWords.length; index++) {
            subWordsChars[index] = subWords[index].toCharArray();
        }
        int[] memo = new int[chars.length];
        Arrays.fill(memo, -1);
        return countWordsRecursive(0, chars, subWordsChars, memo);
    }

    private static int countWordsRecursive(int index, char[] word, char[][] subWords, int[] counts) {
        if (index == word.length) {
            return 1;
        } else if (index > word.length) {
            return 0;
        } else if (counts[index] > -1) {
            return counts[index];
        } else {
            int result = 0;
            for (char[] subWord : subWords) {
                boolean matches = true;
                for (int subIdx = 0; subIdx < subWord.length; subIdx++) {
                    if (word[index + subIdx] != subWord[subIdx]) {
                        matches = false;
                        break;
                    }
                }
                if (matches) {
                    result += countWordsRecursive(index + subWord.length, word, subWords, counts);
                }
            }
            counts[index] = result;
            return result;
        }
    }
}
