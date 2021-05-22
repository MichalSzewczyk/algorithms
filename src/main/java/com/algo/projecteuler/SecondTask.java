package com.algo.projecteuler;

import java.util.Scanner;

public class SecondTask {
    /**
     * Implementation of algorithm for:
     * https://www.hackerrank.com/contests/projecteuler/challenges/euler002/problem
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int idx = 0; idx < t; idx++) {
            System.out.println(computeSum(in.nextLong()));
        }
    }

    static long computeSum(long value) {
        long sum = 2;
        long nMinusOne = 2;
        long nMinusTwo = 0;
        while (true) {
            long current = 4 * nMinusOne + nMinusTwo;
            if (current > value) {
                return sum;
            }
            sum += current;
            nMinusTwo = nMinusOne;
            nMinusOne = current;
        }
    }
}
