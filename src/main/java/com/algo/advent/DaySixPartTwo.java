package com.algo.advent;

import java.util.Arrays;

public class DaySixPartTwo {
    public static void main(String[] args) {
        String input = "Time:        50748685\n" +
                "Distance:   242101716911252";
        String[] lines = input.split("\n");
        long[] time = Arrays.stream(lines[0].replaceAll("Time: [ ]+", "").split(" +")).mapToLong(Long::valueOf).toArray();
        long[] distance = Arrays.stream(lines[1].replaceAll("Distance:[ ]+", "").split(" +")).mapToLong(Long::valueOf).toArray();
        int result = 1;
        for (int idx = 0; idx < time.length; idx++) {
            int waysCount = count(time[idx], distance[idx]);
            result *= waysCount;
        }
        System.out.println(result);
    }

    private static int count(long time, long distance) {
        int count = 0;
        for (int speed = 0; speed <= time; speed++) {
            if ((time - speed) * (speed) > distance) {
                count++;
            }
        }
        return count;
    }
}
