package com.algo.min;

/**
 * Algorithm
 * https://www.codewars.com/kata/624e0a4c3e1d7b0031588666
 * Although its use in screening healthy patients is debatable, Prostate Specific Antigen (PSA) is a very useful blood test for following the response of prostate cancer to treatment.
 * For patients treated with radiation therapy alone for prostate cancer, the PSA tends to take 2-3 years to reach a minimum value (nadir). If the PSA begins to rise again after the nadir value, this may represent a recurrence of prostate cancer.
 * The 1996 ASTRO (American Society for Radiation Oncology) definition (1) of a biochemical recurrence is: 3 consecutive rising PSA values after reaching the nadir value (lowest point) of the sample.
 * You are given a list of PSA values for one patient. The list consists of 12 PSA values in ng/mL (as a float with 2 decimal places) each taken 6 months apart. The first value is taken immediately before treatment. The accuracy is two decimal points. The nadir (minimum value) will appear only once in the list.
 * Return True if the values meet the ASTRO criteria (3 consecutive increases in PSA occuring any time after reaching the lowest value) or False if they do not.
 * For clarity, the 3 consecutive increases may start directly from the nadir but do not have to. See examples below.
 * <p>
 * Examples:
 * Input:
 * [7.91, 2.43, 1.49, 0.99, 0.74, 0.48, 0.52, 0.50, 0.66, 1.26, 1.36, 1.35]
 * Ouput:
 * True
 * # Nadir 0.48,  3 subsequent rises: 0.50->0.66, 0.66->1.26 , 1.26->1.36
 * Input:
 * [9.98, 8.56, 4.62, 1.16, 0.26, 0.37, 0.32, 1.02, 0.99, 1.56, 1.41, 2.35]
 * Ouput:
 * False
 * # Nadir 0.26. No subsequent sequence of 3 consecutive rises.
 * Input:
 * [12.57, 6.86, 1.86, 1.93, 0.60, 1.26, 0.99 ,2.1, 0.70, 0.72, 0.88, 1.9]
 * Output:
 * True
 * # Nadir 0.60, 3 subsequent consecutive rises: 0.70->0.72, 0.72->0.88, 0.88->1.9
 * Input:
 * [14.66, 3.14, 0.53, 0.58, 1.00, 1.26, 0.99 ,2.1, 1.50, 2.53, 2.17, 2.50]
 * Output:
 * True
 * # Nadir 0.53.  3 subsequent consecutive rises: 0.53->0.58, 0.58->1.00, 1.00-> 1.26
 */
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
