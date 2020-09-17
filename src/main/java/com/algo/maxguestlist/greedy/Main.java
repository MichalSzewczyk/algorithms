package com.algo.maxguestlist.greedy;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * Greedy implementation taking guest with smallest amount of excluded guests at first place.
     * This implementation doesn't give correct results.
     *
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
//                {0, 4},
//                {0, 5},
//                {0, 6},
//                {4, 1},
//                {4, 2},
//                {4, 3},
//                {5, 1},
//                {5, 2},
//                {5, 3},
//                {6, 1},
//                {6, 2},
//                {6, 3},
//                {1, 2},
//                {1, 3},
//                {2, 3},

                {0, 1},
                {0, 2},
                {0, 3},
                {1, 4},
                {2, 5},
                {3, 6},
                {1, 2},
                {2, 3},
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
