package com.algo.min;

public class Main {
    public static void main(String[] args) {
        double[] values = new double[]{1.3, 4.5, 1.2, 5.6, 5.7, 5.8, 5.9};
        boolean result = getMinWithThreeGreaterValues(values);
        System.out.println(result);
    }

    private static boolean getMinWithThreeGreaterValues(double[] values) {
        int subsequentIncreasesCount = 0;
        double min = values[values.length - 1];
        boolean result = false;
        for (int i = values.length - 2; i >= 0; i--) {
            if (subsequentIncreasesCount < 3) {
                if (values[i + 1] > values[i]) {
                    subsequentIncreasesCount++;
                } else {
                    subsequentIncreasesCount = 0;
                }
            }
            if (min > values[i]) {
                result = subsequentIncreasesCount >= 3;
                min = values[i];
            }
        }
        return result;
    }
}
