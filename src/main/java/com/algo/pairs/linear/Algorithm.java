package com.algo.pairs.linear;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Algorithm {
    /**
     * You will be given an array of integers and a target value. Determine the number of pairs of array elements that
     * have a difference equal to a target value.
     * For example, given an array of [1, 2, 3, 4] and a target value of 1, we have three values meeting the condition: 2 - 1 = 1, 3 - 2 = 1, and 4 - 3 = 1.
     * <p>
     * Function Description
     * Complete the pairs function below. It must return an integer representing the number of element pairs having the required difference.
     * pairs has the following parameter(s):
     * k: an integer, the target difference
     * arr: an array of integers
     * <p>
     * Sample Input
     * 5 2
     * 1 5 3 4 2
     * <p>
     * Sample Output
     * 3
     */
    static int pairs(int k, int[] arr) {
        Set<Integer> numbers = new HashSet<>();
        int counter = 0;
        for (int number : arr) {
            if (numbers.contains(number - k)) {
                counter++;
            }
            if (numbers.contains(number + k)) {
                counter++;
            }
            numbers.add(number);
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
