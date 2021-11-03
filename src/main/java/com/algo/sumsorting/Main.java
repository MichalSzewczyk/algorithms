package com.algo.sumsorting;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String result = sort("123 354 100");
        System.out.println(result);
    }

    private static String sort(String word) {
        return Arrays
                .stream(word.split(" "))
                .map(Main::getAsNumberWithSum)
                .sorted(Comparator.comparingInt(arr -> arr[1]))
                .map(arr -> arr[0])
                .reduce(
                        new StringBuilder(),
                        (first, second) -> first.append(" ").append(second),
                        StringBuilder::append)
                .toString();
    }

    private static int[] getAsNumberWithSum(String word) {
        int value = Integer.parseInt(word);
        int sum = 0;
        int tmpValue = value;
        while (tmpValue != 0) {
            sum += tmpValue % 10;
            tmpValue /= 10;
        }
        return new int[]{value, sum};
    }
}
