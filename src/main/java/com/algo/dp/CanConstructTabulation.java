package com.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CanConstructTabulation {
    public static void main(String[] args) {
        List<char[]> subWords = canConstruct(new char[]{'b', 'a', 'b', 'b'}, new char[][]{new char[]{'b'}, new char[]{'b', 'b'}, new char[]{'a'}});
        subWords.stream().map(Arrays::toString).forEach(System.out::println);
    }

    private static List<char[]> canConstruct(char[] target, char[][] words) {
        int[] arr = new int[target.length + 1];
        Arrays.fill(arr, -1);
        arr[0] = 0;
        for (int idx = 0; idx < arr.length; idx++) {
            if (arr[idx] != -1) {
                for (int wordIdx = 0; wordIdx < words.length; wordIdx++) {
                    char[] current = words[wordIdx];
                    if (idx + current.length > arr.length - 1) {
                        break;
                    }
                    boolean matches = true;

                    for (int letterIdx = 0; letterIdx < current.length; letterIdx++) {
                        if (target[idx + letterIdx] != current[letterIdx]) {
                            matches = false;
                            break;
                        }
                    }
                    if (matches && arr[idx + current.length] == -1) {
                        arr[idx + current.length] = wordIdx;
                    }
                }
            }
        }
        if (arr[arr.length - 1] == -1) {
            return Collections.emptyList();
        }
        List<char[]> result = new ArrayList<>();
        int idx = arr.length - 1;
        while (idx != 0) {
            char[] word = words[arr[idx]];
            result.add(word);
            idx -= word.length;
        }
        return result;
    }
}
