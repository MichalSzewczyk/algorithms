package com.algo.bits;

public class Main {
    public static void main(String[] args) {
        boolean isEven = isEven(7);
        System.out.println("Is even: " + isEven);
        char upperCased = upperCase('a');
        System.out.println("Upper cased: " + upperCased);
        char lowerCased = lowerCase('A');
        System.out.println("Lower cased: " + lowerCased);
        int setBitsCount = countSetBits(7);
        System.out.println("Bits counted: " + setBitsCount);
        setBitsCount = countSetBitsOptimized(7);
        System.out.println("Bits counted optimized: " + setBitsCount);
    }

    private static boolean isEven(int number) {
        return ((number & 1) == 0);
    }

    private static char upperCase(char character) {
        return (char) (character & ~32);
    }

    private static char lowerCase(char character) {
        return (char) (character | 32);
    }

    private static int countSetBits(int number) {
        int bitCount = 0;
        while (number != 0) {
            if ((number & 1) == 1) {
                bitCount++;
            }
            number >>= 1;
        }
        return bitCount;
    }

    private static int countSetBitsOptimized(int number) {
        int counter = 0;
        while (number != 0) {
            number &= number - 1;
            counter++;
        }
        return counter;
    }
}
