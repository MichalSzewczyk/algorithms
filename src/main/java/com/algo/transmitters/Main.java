package com.algo.transmitters;

import java.util.Scanner;

public class Main {
    /**
     * Hackerland is a one-dimensional city with houses aligned at integral locations along a road. The Mayor wants to install radio transmitters on the roofs of the city's houses. Each transmitter has a fixed
     * range meaning it can transmit a signal to all houses within that number of units distance away. Given a map of Hackerland and the transmission range, determine the minimum number of transmitters so
     * that every house is within range of at least one transmitter. Each transmitter must be installed on top of an existing house.
     * For example, assume houses are located at x = [1, 2, 3, 5, 9] and the transmission range k = 1. 3 antennae at houses 2 and 5 and 9 would provide complete coverage.
     * There is no house at location 7 to cover both 5 and 9. [1, 2, 5], [5] and [9].
     *
     * Hacker rank challenge link: https://www.hackerrank.com/challenges/hackerland-radio-transmitters/problem
     */
    static int hackerlandRadioTransmitters(int[] buildingIndices, int k, int maxIndex) {
        boolean[] road = new boolean[maxIndex + 1];
        for (int buildingIndex : buildingIndices) {
            road[buildingIndex] = true;
        }
        int transmittersCounter = 0;
        for (int index = 0; index < road.length; index++) {
            if (road[index]) {
                int indexToPlace = index + k;
                if (indexToPlace >= road.length) {
                    return transmittersCounter + 1;
                }
                while (!road[indexToPlace]) {
                    indexToPlace--;
                }
                transmittersCounter++;
                index = indexToPlace + k;
            }
        }
        return transmittersCounter;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] x = new int[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        int maxIndex = -1;
        for (int i = 0; i < n; i++) {
            int xItem = Integer.parseInt(xItems[i]);
            x[i] = xItem;
            maxIndex = Math.max(xItem, maxIndex);
        }

        int result = hackerlandRadioTransmitters(x, k, maxIndex);
        System.out.println(result);
        scanner.close();
    }
}
