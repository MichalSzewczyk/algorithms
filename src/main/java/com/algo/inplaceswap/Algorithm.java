package com.algo.inplaceswap;

/**
 * Algorithm for swap of two int variables without any additional memory.
 */
public class Algorithm {
    public static void main(String[] args) {
        int firstValue = 10;
        int secondValue = 20;
        System.out.println(String.format("Before swap. [firstValue=%s] [secondValue=%s]", firstValue, secondValue));
        firstValue += secondValue;
        secondValue = firstValue - secondValue;
        firstValue = firstValue - secondValue;
        System.out.println(String.format("After swap. [firstValue=%s] [secondValue=%s]", firstValue, secondValue));
    }
}
