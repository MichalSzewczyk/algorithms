package com.algo.moveallzeros;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] result = moveAllZeros(new int[]{1, 2, 0, 0, 5, 7, 8});
        System.out.println(Arrays.toString(result));
    }

    static int[] moveAllZeros(int[] arr) {
        int readIdx = arr.length - 1;
        int writeIdx = arr.length - 1;
        while (readIdx != -1) {
            if (arr[readIdx] != 0) {
                arr[writeIdx--] = arr[readIdx--];
            } else {
                readIdx--;
            }
        }
        while (writeIdx != -1) {
            arr[writeIdx--] = 0;
        }
        return arr;
    }


}
