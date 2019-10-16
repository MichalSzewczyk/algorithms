package com.algo.leaderboard;

import java.util.Arrays;

public class Algorithm {
    /**
     * Alice is playing an arcade game and wants to climb to the top of the leaderboard and wants to track her ranking. The game uses Dense Ranking, so its leaderboard works like this:
     * The player with the highest score is ranked number 1 on the leaderboard.
     * Players who have equal scores receive the same ranking number, and the next player(s) receive the immediately following ranking number.
     * The first line contains an integer n, the number of players on the leaderboard.
     * The next line contains n space-separated integers scores[i], the leaderboard scores in decreasing order.
     * The next line contains an integer, m, denoting the number games Alice plays.
     * The last line contains m space-separated integers alice[j], the game scores.
     */
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] result = new int[alice.length];
        int scoresIndex = 0;
        int scoresCount = 0;
        int previousScore = -1;
        int aliceIndex = alice.length - 1;
        while (scoresIndex < scores.length && aliceIndex >= 0) {
            if (previousScore == scores[scoresIndex]) {
                scoresIndex++;
            } else {
                if (scores[scoresIndex] > alice[aliceIndex]) {
                    previousScore = scores[scoresIndex];
                    scoresIndex++;
                    scoresCount++;
                } else if (scores[scoresIndex] < alice[aliceIndex]) {
                    result[aliceIndex] = alice[aliceIndex] == previousScore ? scoresCount : scoresCount + 1;
                    aliceIndex--;
                } else if (scores[scoresIndex] == alice[aliceIndex]) {
                    scoresCount++;
                    result[aliceIndex] = scoresCount;
                    aliceIndex--;
                    previousScore = scores[scoresIndex++];
                }
            }
        }
        while (aliceIndex >= 0) {
            result[aliceIndex] = previousScore == alice[aliceIndex] ? scoresCount : scoresCount + 1;
            aliceIndex--;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(climbingLeaderboard(new int[]{1}, new int[]{1, 1})));
    }

}
