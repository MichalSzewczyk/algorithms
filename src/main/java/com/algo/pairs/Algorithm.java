package com.algo.pairs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Algorithm {
    static int pairs(int k, int[] arr) {
        Arrays.sort(arr);
        int firstIndex = 0;
        int secondIndex = 1;
        int counter = 0;
        while (firstIndex <= arr.length && secondIndex < arr.length) {
            if (arr[secondIndex] - arr[firstIndex] == k) {
                counter++;
                secondIndex++;
            } else if (arr[secondIndex] - arr[firstIndex] > k) {
                firstIndex++;
            } else {
                secondIndex++;
            }
        }
        return counter;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nk = scanner.nextLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[] arr = new int[n];
        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }
        int result = pairs(k, arr);
        System.out.println(result);
        scanner.close();
    }
}
