package com.algo.sherlock;

import com.algo.utils.Counter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Algorithm {
    /**
     * Sherlock considers a string to be valid if all characters of the string appear the same number of times.
     * It is also valid if he can remove just  character at  index in the string, and the remaining characters will occur the same number of times.
     * Given a string, determine if it is valid. If so, return YES, otherwise return NO.
     */
    static String isValid(String word) {
        Collection<Counter> letterOccurrences = getLetterOccurrences(word);
        if (letterOccurrences.size() < 2) {
            return "YES";
        }
        Map<Integer, Counter> occurrencesFrequency = new HashMap<>();
        for (Counter occurrence : letterOccurrences) {
            Counter occurrenceFrequency = occurrencesFrequency.computeIfAbsent(occurrence.getValue(), __ -> new Counter());
            occurrenceFrequency.increment();
        }
        if (occurrencesFrequency.entrySet().size() > 2) {
            return "NO";
        }
        if (occurrencesFrequency.entrySet().size() < 2) {
            return "YES";
        }
        Iterator<Map.Entry<Integer, Counter>> occurrenceFrequency = occurrencesFrequency.entrySet().iterator();
        Map.Entry<Integer, Counter> firstFrequency = occurrenceFrequency.next();
        Map.Entry<Integer, Counter> secondFrequency = occurrenceFrequency.next();

        if (isOneLetterDifference(firstFrequency, secondFrequency) || isOnlyOneLetter(firstFrequency) || isOnlyOneLetter(secondFrequency)) {
            return "YES";
        }

        return "NO";
    }

    private static Collection<Counter> getLetterOccurrences(String word) {
        Map<Character, Counter> letterOccurrences = new HashMap<>();

        char[] letters = word.toCharArray();

        for (char letter : letters) {
            Counter counter = letterOccurrences.computeIfAbsent(letter, __ -> new Counter());
            counter.increment();
        }
        return letterOccurrences.values();
    }

    private static boolean isOneLetterDifference(Map.Entry<Integer, Counter> firstFrequency, Map.Entry<Integer, Counter> secondFrequency) {
        return firstFrequency.getKey() - 1 == secondFrequency.getKey() && firstFrequency.getValue().getValue() == 1 ||
                secondFrequency.getKey() - 1 == firstFrequency.getKey() && secondFrequency.getValue().getValue() == 1;
    }

    private static boolean isOnlyOneLetter(Map.Entry<Integer, Counter> firstFrequency) {
        return firstFrequency.getKey() == 1 && firstFrequency.getValue().getValue() == 1;
    }
}
