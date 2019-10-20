package com.algo.nondivisiblesubset;

import java.util.*;

public class Algorithm {
    /**
     * Given a set of distinct integers, print the size of a maximal subset of S where the sum of any 2 numbers in subset is not evenly divisible by k.
     */
    public static int nonDivisibleSubset(int k, List<Integer> numbers) {
        // Write your code here
        Map<Integer, Number> occurrences = new HashMap<>();
        for (Integer currentNumber : numbers) {
            Number number = occurrences.computeIfAbsent(currentNumber % k, Number::new);
            number.increment();
        }
        List<Number> numbersWithOccurrences = new ArrayList<>(occurrences.values());
        numbersWithOccurrences.sort(Comparator.comparingInt(Number::getCounter).reversed());
        Set<Integer> excludedNumbers = new HashSet<>();
        int resultCounter = 0;
        for (Number number : numbersWithOccurrences) {
            if (!excludedNumbers.contains(number.getValue())) {
                excludedNumbers.add(k - number.getValue());
                if(0 == ((number.getValue() * 2) % k)){
                    resultCounter++;
                }else {
                    resultCounter += number.getCounter();
                }
            }
        }
        return resultCounter;
    }

    public static class Number {
        private int counter;
        private int value;

        public Number(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getCounter() {
            return counter;
        }

        public void increment() {
            counter++;
        }
    }

    public static void main(String[] args) {
        System.out.println(nonDivisibleSubset(1, Arrays.asList(1, 2, 3, 4, 5)));
    }
}
