package com.algo.minimumloss;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    /*
     * Lauren has a chart of distinct projected prices for a house over the next several years. She must buy the house in one year and sell it in another,
     * and she must do so at a loss. She wants to minimize her financial loss.
     * For example, the house is valued at price = [20, 15, 8, 2, 12] over the next n = 5 years. She can purchase the home in any year, but she must resell
     * the house at a loss in one of the following years.
     * Her minimum loss would be incurred by purchasing in year 2 at price[1] = 15 and reselling in year 2 at price[1] = 15.
     * Find and print the minimum amount of money Lauren must lose if she buys the house and resells it within the next n years.
     * Note: It's guaranteed that a valid answer exists.
     * Function Description
     * Complete the minimumLoss function in the editor below. It should return an integer that represents the minimum loss that can be achieved.
     * minimumLoss has the following parameter(s):
     */
    static long minimumLoss(long[] prices) {
        TreeSet<Long> previousPrices = new TreeSet<>();
        long result = Long.MAX_VALUE;
        for (long price : prices) {
            Long higher = previousPrices.higher(price);
            if (higher != null) {
                long diff = higher - price;
                result = Math.min(result, diff);
            }
            previousPrices.add(price);
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] price = new long[n];

        String[] priceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long priceItem = Long.parseLong(priceItems[i]);
            price[i] = priceItem;
        }

        long result = minimumLoss(price);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
