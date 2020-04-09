package com.algo.anagrams;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Algorithm {
    static int sherlockAndAnagrams(String input) {
        Map<String, Counter> counters = new HashMap<>();
        for (int i = 1; i < input.length(); i++) {
            for (int j = 0; j < input.length() - i + 1; j++) {
                String currentSubWord = input.substring(j, j + i);
                String currentSubWordSorted = sort(currentSubWord);
                counters.computeIfAbsent(currentSubWordSorted, __ -> new Counter()).increment();
            }
        }
        return computeAnagrams(counters.values());
    }

    private static int computeAnagrams(Collection<Counter> values) {
        return values.stream().mapToInt(Counter::getValue).filter(value -> value > 1).map(value -> binomialCoeff(value, 2)).sum();
    }

    static int binomialCoeff(int n, int k) {
        if (k == 0 || k == n)
            return 1;
        return binomialCoeff(n - 1, k - 1) +
                binomialCoeff(n - 1, k);
    }

    private static class Counter {
        private int value;

        public void increment() {
            value++;
        }

        public int getValue() {
            return value;
        }

        public String toString() {
            return String.valueOf(value);
        }
    }

    private static String sort(String text) {
        char[] chars = text.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
