package com.algo.dp;

public class FibonacciRecursive {
    public static void main(String[] args) {
        System.out.println(fib(25));
    }

    private static int fib(int n) {
        if (n <= 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
