package com.algo.countmaxpalindromes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    /*
     * Complete the 'answerQuery' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER r
     */

    final static long MOD = 1000000007;
    static long[][] palindromeCounts;

    public static void initialize(char[] word) {
        palindromeCounts = new long[word.length][word.length];
        for (int xIdx = 0; xIdx < word.length; xIdx++) {
            int[] letterCounts = new int['z' - 'a' + 1];
            letterCounts[word[xIdx] - 'a']++;
            palindromeCounts[xIdx][xIdx] = 1;
            int oddCount = 1;
            int evenCount = 0;
            for (int yIdx = xIdx + 1; yIdx < word.length; yIdx++) {
                long current = palindromeCounts[xIdx][yIdx - 1];
                letterCounts[word[yIdx] - 'a']++;
                if (letterCounts[word[yIdx] - 'a'] % 2 == 0) {
                    oddCount--;
                    evenCount++;
                    if (oddCount > 0) {
                        current = (((current / (oddCount + 1)) * evenCount) / (letterCounts[word[yIdx] - 'a'] / 2)) * oddCount;
                    } else {
                        current = (((current / (oddCount + 1)) * evenCount) / (letterCounts[word[yIdx] - 'a'] / 2));
                    }
                    palindromeCounts[xIdx][yIdx] = current;
                } else {
                    oddCount++;
                    if(oddCount > 1) {
                        current = (current / (oddCount - 1)) * oddCount;
                    }
                    palindromeCounts[xIdx][yIdx] = current;
                }

            }
        }
    }

    public static int answerQuery(int l, int r) {
        long result = palindromeCounts[l][r];
        if (l == r) {
            return 1;
        }
        return (int) result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();
        Main.initialize(s.toCharArray());
        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int l = Integer.parseInt(firstMultipleInput[0]) - 1;

                int r = Integer.parseInt(firstMultipleInput[1]) - 1;
                char[] word = s.toCharArray();
                initialize(word);
                int result = answerQuery(l, r);
                System.out.println(result);
//                System.out.println(Arrays.deepToString(palindromeCounts));

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bufferedReader.close();
    }
}
