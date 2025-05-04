package com.algo.longestpalindrome;

public class TwoPointers {
    public static void main(String[] args) {
        String result = longestPalindrome("babad");
        System.out.println(result);
    }

    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int minStartIdx = Integer.MAX_VALUE;
        int minEndIdx = Integer.MAX_VALUE;
        for (int idx = 0; idx < chars.length; idx++) {
            int startIdx = idx;
            int endIdx = idx;
            while(startIdx >= 0 && endIdx < chars.length) {
                if(chars[startIdx] == chars[endIdx]) {
                    if(endIdx - startIdx  + 1 > minEndIdx - minStartIdx) {
                        minStartIdx = startIdx;
                        minEndIdx = endIdx;
                    }
                    startIdx--;
                    endIdx++;
                } else {
                    break;
                }
            }
            startIdx = idx;
            endIdx = idx + 1;
            while(startIdx >= 0 && endIdx < chars.length) {
                if(chars[startIdx] == chars[endIdx]) {
                    if(endIdx - startIdx > minEndIdx - minStartIdx) {
                        minStartIdx = startIdx;
                        minEndIdx = endIdx;
                    }
                    startIdx--;
                    endIdx++;
                } else {
                    break;
                }
            }
        }
        return s.substring(minStartIdx, minEndIdx + 1);
    }
}
