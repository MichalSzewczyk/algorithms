package com.algo.maxguestlist;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * Problem
     * Given list of mutually exclusive guests.
     * Find the maximum guest list with respect to aforementioned exclusions.
     * <p>
     * Example
     * Exclusions:
     * 0 - 1
     * 0 - 2
     * 2 - 3
     * 1 - 4
     * Maximum guests list:
     * 0, 3, 4
     */
    public static void main(String[] args) {
        int[][] excludingPairs = new int[][]{
                {0, 1},
                {0, 2},
                {2, 3},
                {1, 4},
        };
        Collection<Integer> guestList = computeGuestsCollection(excludingPairs);
        System.out.println(guestList);
    }

    private static Collection<Integer> computeGuestsCollection(int[][] excludingPairs) {
        Map<Integer, Set<Integer>> exclusions = new HashMap<>();
        for (int[] pair : excludingPairs) {
            Collection<Integer> excludedFirst = exclusions.computeIfAbsent(pair[0], __ -> new HashSet<>());
            excludedFirst.add(pair[1]);
            Collection<Integer> excludedSecond = exclusions.computeIfAbsent(pair[1], __ -> new HashSet<>());
            excludedSecond.add(pair[0]);
        }
        Collection<Integer> sortedGuestsWithNumberOfExcluded = exclusions
                .entrySet()
                .stream()
                .map(entry -> new int[]{entry.getKey(), entry.getValue().size()})
                .sorted(Comparator.comparingInt(value -> value[1])).map(value -> value[0])
                .collect(Collectors.toList());
        Collection<Integer> guests = new HashSet<>();
        for (int guest : sortedGuestsWithNumberOfExcluded) {
            Collection<Integer> excludedGuests = exclusions.get(guest);
            if (isIntersectionEmpty(guests, excludedGuests)) {
                guests.add(guest);
            }
        }
        return guests;
    }

    private static <T> boolean isIntersectionEmpty(Collection<T> firstCollection, Collection<T> secondCollection) {
        for (T element : secondCollection) {
            if (firstCollection.contains(element)) {
                return false;
            }
        }
        return true;
    }
}
