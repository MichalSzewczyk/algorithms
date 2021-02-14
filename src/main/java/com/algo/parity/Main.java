package com.algo.parity;

public class Main {
    /**
     * Task:
     * Parity of the number determines if number of 1s is odd or even:
     * - parity is 1 if number is odd
     * - parity is 0 if number is even
     * Given number, compute its parity.
     * <p>
     * Notes
     * Java bitwise operators:
     * - ~      - bitwise negation              - inverts bit mask: ~0b101 => 0b11111111111111111111111111111010
     * - >>     - signed bitwise right shift    - shifts bits to the right: 0b1001 >> 1 => 0b100
     * - <<     - signed bitwise left shift     - shifts bits to the left: 0b1001 << 1 => 0b10010
     * - >>>    - unsigned bitwise right shift  - shifts bits to the right adding zeros regardless of sign: 0b11111111111111111111111111111110 >>> 1 => 0b01111111111111111111111111111111
     * - ^      - bitwise xor                   - applies xor between two bit masks: 0b1100 ^ 0b1010 => 0b0110
     * - |      - bitwise or                    - applies or between two bit masks: 0b1100 | 0b1010 => 0b1110
     * - &      - bitwise and                   - applies and between two bit masks: 0b1100 & 0b1010 => 0b1000
     *
     * Java number systems:
     * - Hexadecimal:   0xa                     - prefix 0x indicates that number is written in hexadecimal system
     * - Decimal:       10                      - default number system
     * - Binary:        0b1                     - prefix 0d indicates that number is written in binary system
     *
     */
    public static void main(String[] args) {
        short parity = computeParity(0b10011111);
        System.out.println(parity);
    }

    private static short computeParity(long number) {
        short parity = 0;
        while (number != 0) {
            parity ^= number & 0b1;
            number >>= 1;
        }
        return parity;
    }
}
