package com.algo.beautifularray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    static int lilysHomework(int[] arr) {
        int countDescending = countSteps(arr, false);
        int countAscending = countSteps(arr, true);
        return Math.min(countDescending, countAscending);
    }

    private static int countSteps(int[] arr, boolean isAscending) {
        int[] array = Arrays.copyOf(arr, arr.length);
        Map<Integer, Integer> numberToIndexMapping = new HashMap<>();
        for (int index = 0; index < array.length; index++) {
            numberToIndexMapping.put(array[index], index);
        }
        int[] original = Arrays.copyOf(array, array.length);
        sort(array, isAscending);
        int counter = 0;
        for (int index = 0; index < array.length; index++) {
            if (original[index] != array[index]) {
                int indexOfCurrentElement = numberToIndexMapping.get(array[index]);
                int tmp = original[index];
                original[index] = original[indexOfCurrentElement];
                original[indexOfCurrentElement] = tmp;
                numberToIndexMapping.put(tmp, indexOfCurrentElement);
                counter++;
            }
        }
        return counter;
    }

    private static void sort(int[] arr, boolean isAscending) {
        if (isAscending) {
            Arrays.sort(arr);
        } else {
            for (int index = 0; index < arr.length; index++) {
                arr[index] = -arr[index];
            }
            Arrays.sort(arr);
            for (int index = 0; index < arr.length; index++) {
                arr[index] = -arr[index];
            }
        }
    }


    public static void main(String[] args) {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = lilysHomework(arr);

        System.out.println(result);
        scanner.close();
    }
}
