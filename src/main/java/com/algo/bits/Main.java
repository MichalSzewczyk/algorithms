package com.algo.bits;

import java.util.Arrays;

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
        int firstNonRepeatedElement = findNonRepeatedElement(new int[]{3, 4, 5, 3, 4});
        System.out.println("Non repeated value: " + firstNonRepeatedElement);
        int[] nonRepeatedElements = findNonRepeatedElements(new int[]{3, 4, 5, 3, 1, 4});
        System.out.println("Two non repeated elements: " + Arrays.toString(nonRepeatedElements));
        int firstSetBit = findFirstSetBit(5);
        System.out.println("First set bit position from the right is: " + firstSetBit);
        boolean wordParity = isPair(8);
        System.out.println("Parity: " + wordParity);
        boolean optimizedWordParity = isPairOptimized(8);
        System.out.println("Optimized word parity: " + optimizedWordParity);
        int swapped = swapBits(4, 1, 2);
        System.out.println("Swapped bits: " + swapped);
        int valueToReverse = 0b101011;
        int reversed = reverseBits(valueToReverse);
        System.out.println("Reversed bits: " + Integer.toBinaryString(reversed) + ", before reverse: " + Integer.toBinaryString(valueToReverse));
        int signedValueToReverse = 0b11111111111111111111111111111011;
        int signedReversed = reverseBitsSignedInteger(signedValueToReverse);
        System.out.println("Reversed signed bits: " + Integer.toBinaryString(signedReversed) + ", before reverse: " + Integer.toBinaryString(signedValueToReverse));
    }

    private static int reverseBits(int value) {
        int firstMask = 1 << ((int) (Math.log(value) / Math.log(2)));
        int secondMask = 1;
        while (firstMask > secondMask) {
            if (((value & firstMask) == firstMask) != ((value & secondMask) == secondMask)) {
                value ^= firstMask;
                value ^= secondMask;
            }
            firstMask >>= 1;
            secondMask <<= 1;
        }
        return value;
    }

    public static int reverseBitsSignedInteger(int n) {
        int rightMask = 1;
        int leftMask = 1 << 31;
        if (((n & rightMask) == 0) != ((n & leftMask) == 0)) {
            n ^= rightMask;
            n ^= leftMask;
        }
        rightMask <<= 1;
        leftMask = 1 << 30;
        while (rightMask < leftMask) {
            if (((n & rightMask) == 0) != ((n & leftMask) == 0)) {
                n ^= rightMask;
                n ^= leftMask;
            }
            rightMask <<= 1;
            leftMask >>= 1;
        }
        return n;
    }

    private static int swapBits(int number, int first, int second) {
        if (((number >> first) & 1) != ((number >> second) & 1)) {
            int bitSwapMask = (1 << first) | (1 << second);
            number ^= bitSwapMask;
        }
        return number;
    }

    private static boolean isPair(int value) {
        boolean result = true;
        while (value != 0) {
            result = result ^ ((value) & 1) == 1;
            value >>= 1;
        }
        return result;
    }

    private static boolean isPairOptimized(int value) {
        boolean result = true;
        while (value != 0) {
            result = !result;
            value &= value - 1;
        }
        return result;
    }

    private static int findFirstSetBit(int value) {
        int position = 0;
        while (true) {
            if (((value >> position) & 1) == 0) {
                position++;
            } else {
                return position;
            }
        }
    }

    private static int[] findNonRepeatedElements(int[] elements) {
        int xorResult = Arrays.stream(elements)
                .reduce(Main::xor)
                .orElseThrow(IllegalAccessError::new);
        int firstNonZeroBit = 1;
        while ((firstNonZeroBit & xorResult) == 0) {
            firstNonZeroBit *= 2;
        }
        int nonZeroBit = firstNonZeroBit;
        int[] elementsWithNonZeroBit = Arrays.stream(elements).filter(element -> (element & nonZeroBit) != 0).toArray();
        int[] elementsWithZeroBit = Arrays.stream(elements).filter(element -> (element & nonZeroBit) == 0).toArray();
        int firstNumber = Arrays.stream(elementsWithNonZeroBit)
                .reduce(Main::xor)
                .orElseThrow(IllegalAccessError::new);
        int secondNumber = Arrays.stream(elementsWithZeroBit)
                .reduce(Main::xor)
                .orElseThrow(IllegalAccessError::new);
        return new int[]{firstNumber, secondNumber};
    }

    private static int xor(int first, int second) {
        return first ^ second;
    }

    private static int findNonRepeatedElement(int[] elements) {
        return Arrays.stream(elements)
                .reduce(Main::xor)
                .orElseThrow(IllegalArgumentException::new);
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
