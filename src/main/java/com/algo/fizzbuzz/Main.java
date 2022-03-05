package com.algo.fizzbuzz;

import java.util.LinkedList;
import java.util.List;

/**
 * Below class shows different approaches to solve <a href="https://en.wikipedia.org/wiki/Fizz_buzz">FizzBuzz</a> game.
 */
public class Main {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    private static final String FIZZ_BUZZ = FIZZ + BUZZ;

    public static void main(String[] args) {
        List<String> resultFirst = fizzBuzzFirst(16);
        System.out.println(resultFirst);
        List<String> resultSecond = fizzBuzzSecond(16);
        System.out.println(resultSecond);
        List<String> resultThird = fizzBuzzThird(16);
        System.out.println(resultThird);
    }

    /**
     * Three if statements, no nested if statements
     */
    private static List<String> fizzBuzzFirst(int limit) {
        List<String> result = new LinkedList<>();
        for (int number = 1; number <= limit; number++) {
            StringBuilder builder = new StringBuilder();
            if (number % 3 == 0) {
                builder.append(FIZZ);
            }
            if (number % 5 == 0) {
                builder.append(BUZZ);
            }
            if (builder.length() == 0) {
                builder.append(number);
            }
            result.add(builder.toString());
        }
        return result;
    }

    /**
     * Three if statements with nested if
     */
    private static List<String> fizzBuzzSecond(int limit) {
        List<String> result = new LinkedList<>();
        for (int number = 1; number <= limit; number++) {
            StringBuilder builder = new StringBuilder();
            if (!(number % 3 == 0) && !(number % 5 == 0)) {
                builder.append(number);
            } else {
                if (number % 3 == 0) {
                    builder.append(FIZZ);
                }
                if (number % 5 == 0) {
                    builder.append(BUZZ);
                }
            }
            result.add(builder.toString());
        }
        return result;
    }

    /**
     * Four if statements, no nested if statements
     */
    private static List<String> fizzBuzzThird(int limit) {
        List<String> result = new LinkedList<>();
        for (int number = 1; number <= limit; number++) {
            StringBuilder builder = new StringBuilder();
            if (number % 15 == 0) {
                builder.append(FIZZ_BUZZ);
            } else if (number % 3 == 0) {
                builder.append(FIZZ);
            } else if (number % 5 == 0) {
                builder.append(BUZZ);
            } else {
                builder.append(number);
            }
            result.add(builder.toString());
        }
        return result;
    }
}
