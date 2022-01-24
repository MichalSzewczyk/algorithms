package com.algo.apartments;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int[] apartment = findApartment(new boolean[][]{
                new boolean[]{true, true, false},
                new boolean[]{false, false, false},
                new boolean[]{false, false, true},
        });
        System.out.println(Arrays.toString(apartment));
    }

    private static int[] findApartment(boolean[][] booleans) {
        int numberOfBuildings = booleans[0].length;
        IntList[] locationsOfBuildings = new IntList[numberOfBuildings];
        for (int building = 0; building < locationsOfBuildings.length; building++) {
            locationsOfBuildings[building] = new IntList();
        }
        for (int location = 0; location < booleans.length; location++) {
            for (int buidling = 0; buidling < numberOfBuildings; buidling++) {
                if (booleans[location][buidling]) {
                    locationsOfBuildings[buidling].addLast(location);
                }
            }
        }
        int[] costs = new int[booleans.length];
        for (int apartment = 0; apartment < booleans.length; apartment++) {
            int currentCost = 0;
            for (int building = 0; building < numberOfBuildings; building++) {
                int closestBuilding = findClosestBuilding(locationsOfBuildings[building], apartment);
                currentCost += closestBuilding;
            }
            costs[apartment] = currentCost;
        }
        int lowestCost = Integer.MAX_VALUE;
        int index = -1;
        System.out.println(Arrays.toString(costs));
        for (int idx = 0; idx < costs.length; idx++) {
            if (lowestCost > costs[idx]) {
                lowestCost = costs[idx];
                index = idx;
            }
        }
        return new int[]{lowestCost, index};
    }

    private static int findClosestBuilding(IntList locationsOfBuilding, int apartment) {
        if(locationsOfBuilding.size() > 1) {
            int first = locationsOfBuilding.get(0);
            int second = locationsOfBuilding.get(1);
            int distanceToFirst = Math.abs(apartment - first);
            int distanceToSecond = Math.abs(apartment - second);
            if (distanceToFirst >= distanceToSecond) {
                locationsOfBuilding.remove(0);
                return distanceToSecond;
            }
            return distanceToFirst;
        } else {
            return Math.abs(apartment - locationsOfBuilding.get(0));
        }
    }

    static class IntList extends LinkedList<Integer> {
    }
}
