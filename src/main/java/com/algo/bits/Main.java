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
        int flipsCount = countFlipsToGetSum(4, 4, 12);
        System.out.println("Flips required: " + flipsCount);
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

    private static int countFlipsToGetSum(int firstNumber, int secondNumber, int targetSum) {
        int flips = 0;
        boolean isOverflow = false;
        for (int bit = 0; bit < 32; bit++) {
            int firstNumberBit = firstNumber >> bit & 1;
            int secondNumberBit = secondNumber >> bit & 1;
            int targetSumBit = targetSum >> bit & 1;
            int currentBit;
            if ((firstNumberBit & secondNumberBit) == 1) {
                if (isOverflow) {
                    currentBit = 1;
                } else {
                    currentBit = 0;
                }
                isOverflow = true;
            } else if ((firstNumberBit | secondNumberBit) == 1) {
                if (isOverflow) {
                    currentBit = 0;
                    isOverflow = true;
                } else {
                    currentBit = 1;
                }
            } else {
                if (isOverflow) {
                    currentBit = 1;
                    isOverflow = false;
                } else {
                    currentBit = 0;
                }
            }
            if (currentBit != targetSumBit) {
                flips++;
            }
        }
        return flips;
    }
}
