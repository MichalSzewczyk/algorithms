package com.algo.exponentation;

public class Main {
    public static void main(String[] args) {
        int result = pow(2, 10);
        System.out.println(result);
    }

    private static int pow(int base, int power) {
        int result = base;
        if (power == 0) {
            return 1;
        }
        while (power != 0) {
            if (power % 2 == 0) {
                base *= base;
                power /= 2;
            } else {
                result *= base;
                power--;
            }
        }
        return result;
    }
}
