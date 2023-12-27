package com.algo.advent;

import java.util.Arrays;

public class DaySixPartOne {
    public static void main(String[] args) {
        String input = "Time:        50     74     86     85\n" +
                "Distance:   242   1017   1691   1252";
        String[] lines = input.split("\n");
        int[] time = Arrays.stream(lines[0].replaceAll("Time: [ ]+", "").split(" +")).mapToInt(Integer::valueOf).toArray();
        int[] distance = Arrays.stream(lines[1].replaceAll("Distance:[ ]+", "").split(" +")).mapToInt(Integer::valueOf).toArray();
        int result = 1;
        for (int idx = 0; idx < time.length; idx++) {
            int waysCount = count(time[idx], distance[idx]);
            result *= waysCount;
        }
        System.out.println(result);
    }

    private static int count(int time, int distance) {
        int count = 0;
        for (int speed = 0; speed <= time; speed++) {
            if ((time - speed) * (speed) > distance) {
                count++;
            }
        }
        return count;
    }
}
