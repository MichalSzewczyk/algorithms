package com.algo.dp;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib(10));
    }

    private static int fib(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        for (int idx = 2; idx < n; idx++) {
            arr[idx] = arr[idx - 2] - arr[idx - 1];
        }
        return arr[n - 1];
    }
}
