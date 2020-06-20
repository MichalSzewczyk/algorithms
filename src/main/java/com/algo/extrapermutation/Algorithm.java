package com.algo.extrapermutation;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Algorithm {
    /**
     * Postman needs to visit each house in his list in given order.
     * He knows all the possible combinations of streets which forms the path between each two subsequent houses in his list.
     * However to make his daily journey more interesting he wanted to have different combinations of paths every day.
     * To have him achieve this, we need to generate all possible combinations of streets which will form valid path.
     */
    public static void main(String[] args) {
        String[][][] streets =
                {
                        {
                                {"Street1", "Street2", "Street3"},
                                {"Street4", "Street2", "Street3"},
                                {"Street5", "Street6"},
                        },
                        {
                                {"Street7"},
                        },
                        {
                                {"Street8", "Street9", "Street10", "Street11"},
                                {"Street12", "Street13", "Street14", "Street15"},
                        }
                };
        String[][] generatedPaths = generateAllPaths(streets);
        System.out.println(Arrays.deepToString(generatedPaths));
    }

    private static String[][] generateAllPaths(String[][][] streets) {
        int[] radixes = Arrays.stream(streets).mapToInt(array -> array.length).toArray();
        MultiRadixCounter counter = new MultiRadixCounter(radixes);
        int numberOfCombinations = Arrays.stream(radixes).reduce(1, (first, second) -> first * second);
        String[][] allPaths = new String[numberOfCombinations][];
        for (int index = 0; index < numberOfCombinations - 1; index++) {
            int[] currentValue = counter.getValue();
            allPaths[index] = IntStream.range(0, streets.length).mapToObj(currentIndex -> streets[currentIndex][currentValue[currentIndex]]).flatMap(Arrays::stream).toArray(String[]::new);
            counter.increment();
        }
        int[] currentValue = counter.getValue();
        allPaths[numberOfCombinations - 1] = IntStream.range(0, streets.length).mapToObj(currentIndex -> streets[currentIndex][currentValue[currentIndex]]).flatMap(Arrays::stream).toArray(String[]::new);
        return allPaths;
    }

    private static class MultiRadixCounter {
        private final int[] radixes;
        private final int[] positionalCounters;
        private int currentIndex;

        private MultiRadixCounter(int[] radixes) {
            this.radixes = radixes;
            positionalCounters = new int[radixes.length];
        }

        int[] getValue() {
            int[] result = new int[positionalCounters.length];
            System.arraycopy(positionalCounters, 0, result, 0, result.length);
            return result;
        }

        void increment() {
            if (positionalCounters[currentIndex] + 1 == radixes[currentIndex]) {
                for (int index = currentIndex; index >= 0; index--) {
                    positionalCounters[index] = 0;
                }
                int nextNonSingleRadix = 1;
                while (radixes[currentIndex + nextNonSingleRadix] <= 1) {
                    nextNonSingleRadix++;
                }
                positionalCounters[currentIndex + nextNonSingleRadix]++;
                currentIndex = 0;
            } else {
                positionalCounters[currentIndex]++;
            }
        }
    }

}
