package com.algo.wordclock;

import java.util.HashMap;
import java.util.Map;

public class Algorithm {
    /**
     * Given the time in numerals we may convert it into words
     */
    static Map<Integer, String> stringRepresentationOfNumber = new HashMap<>();
    static {
        stringRepresentationOfNumber.put(0, "zero");
        stringRepresentationOfNumber.put(1, "one");
        stringRepresentationOfNumber.put(2, "two");
        stringRepresentationOfNumber.put(3, "three");
        stringRepresentationOfNumber.put(4, "four");
        stringRepresentationOfNumber.put(5, "five");
        stringRepresentationOfNumber.put(6, "six");
        stringRepresentationOfNumber.put(7, "seven");
        stringRepresentationOfNumber.put(8, "eight");
        stringRepresentationOfNumber.put(9, "nine");
        stringRepresentationOfNumber.put(10, "ten");
        stringRepresentationOfNumber.put(11, "eleven");
        stringRepresentationOfNumber.put(12, "twelve");
        stringRepresentationOfNumber.put(13, "thirteen");
        stringRepresentationOfNumber.put(14, "fourteen");
        stringRepresentationOfNumber.put(15, "fifteen");
        stringRepresentationOfNumber.put(16, "sixteen");
        stringRepresentationOfNumber.put(17, "seventeen");
        stringRepresentationOfNumber.put(18, "eighteen");
        stringRepresentationOfNumber.put(19, "nineteen");
        stringRepresentationOfNumber.put(20, "twenty");
        stringRepresentationOfNumber.put(30, "thirty");
        stringRepresentationOfNumber.put(40, "fourty");
        stringRepresentationOfNumber.put(50, "fifty");
        stringRepresentationOfNumber.put(60, "sixty");
    }
    // Complete the timeInWords function below.
    static String timeInWords(int h, int m) {
        if(m == 0) {
            return stringRepresentationOfNumber.get(h) + " o' clock";
        }
        String textRepresentation = getMinutesRepresentation(m);
        if(m > 30) {
            h++;
        }
        return textRepresentation + " " + stringRepresentationOfNumber.get(h);

    }

    static String getMinutesRepresentation(int minutes) {
        if(minutes == 0) {
            return "o'clock";
        }
        String representation;
        int minutesAdjusted;
        if (minutes <= 30) {
            representation = " past";
            minutesAdjusted = minutes;
        } else {
            representation = " to";
            minutesAdjusted = 60 - minutes;
        }
        if(minutesAdjusted == 15) {
            return "quarter" + representation;
        }
        if (minutesAdjusted == 30) {
            return "half" + representation;
        }
        if(minutesAdjusted > 1) {
            representation = " minutes" + representation;
        } else {
            representation = " minute" + representation;
        }
        if(minutesAdjusted / 10 <= 1 || minutesAdjusted % 10 == 0) {
            return stringRepresentationOfNumber.get(minutesAdjusted) + representation;
        }
        return stringRepresentationOfNumber.get(minutesAdjusted - (minutesAdjusted % 10)) + " " + stringRepresentationOfNumber.get(minutesAdjusted % 10) + representation;
    }
}
