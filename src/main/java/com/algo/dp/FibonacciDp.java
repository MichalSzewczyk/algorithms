package com.algo.dp;

public class FibonacciDp {
    public static void main(String[] args) {
        System.out.println(fib(50));
    }

    private static long fib(int n) {
        if (n <= 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        } else {
            long[] fibValues = new long[n];
            fibValues[0] = 1;
            fibValues[1] = 1;
            for (int index = 2; index < n; index++) {
                fibValues[index] = fibValues[index - 1] + fibValues[index - 2];
            }
            return fibValues[n - 1];
        }
    }
}
