package com.algo.longfactorials;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Algorithm {
    /**
     * The factorial of the integer n, written n!, is defined as:
     * n! = n x (n - 1) x (n - 2) x ... x n - 2 x n - 1
     * Calculate and print the factorial of a given integer.
     */
    static void extraLongFactorials(int n) {
        List<Integer> resultDigits = getAsDigitArray(n);

        for (int i = n - 1; i > 0; i--) {
            List<Integer> currentDigits = getAsDigitArray(i);
            resultDigits = multiply(resultDigits, currentDigits);
        }
        printDigits(resultDigits);
    }

    static List<Integer> multiply(List<Integer> firstDigits, List<Integer> secondDigits) {
        List<List<Integer>> multiplicationResults = new ArrayList<>();
        for (int index = 0; index < firstDigits.size(); index++) {
            List<Integer> currentResult = multiplyByDigit(firstDigits.get(index), secondDigits);
            currentResult = appendZeros(currentResult, index);
            multiplicationResults.add(currentResult);
        }
        List<Integer> resultDigits = new ArrayList<>();
        for (List<Integer> multiplicationResult : multiplicationResults) {
            resultDigits = sum(resultDigits, multiplicationResult);
        }

        return resultDigits;
    }


    static List<Integer> appendZeros(List<Integer> list, int amount) {
        for (int index = 0; index < amount; index++) {
            list.add(0, 0);
        }
        return list;
    }

    static List<Integer> multiplyByDigit(int digit, List<Integer> digits) {
        List<Integer> multiplicationResults = new ArrayList<>();
        int reminder = 0;
        for (Integer currentDigit : digits) {
            int multiplicationResult = currentDigit * digit + reminder;
            multiplicationResults.add(multiplicationResult % 10);
            reminder = multiplicationResult / 10;
        }
        if (reminder != 0) {
            multiplicationResults.add(reminder);
        }
        return multiplicationResults;
    }

    static List<Integer> sum(List<Integer> firstDigits, List<Integer> secondDigits) {
        if (firstDigits.size() > secondDigits.size()) {
            List<Integer> tempDigits = firstDigits;
            firstDigits = secondDigits;
            secondDigits = tempDigits;
        }
        int lastReminder = 0;
        List<Integer> resultDigits = new ArrayList<>();
        for (int i = 0; i < secondDigits.size(); i++) {
            int sum;
            if (firstDigits.size() <= i) {
                sum = secondDigits.get(i) + lastReminder;
            } else {
                sum = firstDigits.get(i) + secondDigits.get(i) + lastReminder;
            }
            lastReminder = sum / 10;
            resultDigits.add(sum % 10);
        }
        if (lastReminder != 0) {
            resultDigits.add(lastReminder);
        }
        return resultDigits;
    }

    static List<Integer> getAsDigitArray(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n != 0) {
            digits.add(n % 10);
            n = n / 10;
        }
        return digits;
    }

    static void printDigits(List<Integer> digits) {
        StringBuilder stringBuilder = new StringBuilder();
        digits.forEach(stringBuilder::append);
        System.out.println(stringBuilder.reverse());
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        extraLongFactorials(n);

        scanner.close();
    }
}
