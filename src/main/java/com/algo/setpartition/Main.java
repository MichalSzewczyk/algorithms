package com.algo.setpartition;

public class Main {
    public static void main(String[] args) {
        int[] set = new int[]{1, 5, 3};
        boolean result = canBePartitioned(set);
        System.out.println(result);
    }

    private static boolean canBePartitioned(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int partitionSum = sum / 2;
        int[] indexes = new int[partitionSum];
        for (int currentSum = 0; currentSum < partitionSum; currentSum++) {
            int lastIndex = indexes[currentSum];
            if (lastIndex != 0 || currentSum == 0) {
                for (int index = currentSum == 0 ? 0 : lastIndex + 1; index < numbers.length; index++) {
                    int nextSum = currentSum + numbers[index];
                    if (nextSum == partitionSum) {
                        return true;
                    }
                    if (indexes[nextSum] == 0 || indexes[nextSum] > index) {
                        indexes[nextSum] = index;
                    }
                }
            }
        }
        return false;
    }
}
