package com.algo.palindrome;

public class Algorithm {
    /**
     * Palindromes are strings that read the same from the left or right, for example madam or 0110.
     * You will be given a string representation of a number and a maximum number of changes you can make.
     * Alter the string, one digit at a time, to create the string representation of the largest number possible given the limit
     * to the number of changes. The length of the string may not be altered, so you must consider 's left of all higher digits in your tests.
     * For example  is valid,  is not. Given a string representing the starting number and a maximum number of changes allowed, create the largest
     * palindromic string of digits possible or the string -1 if it's impossible to create a palindrome under the contstraints.
     */
    static String highestValuePalindrome(String s, int n, int k) {
        char[] letters = s.toCharArray();
        int changesNeeded = 0;
        for (int index = 0; index < letters.length / 2; index++) {
            if (letters[index] != letters[letters.length - index - 1]) {
                changesNeeded++;
            }
        }
        if (changesNeeded > k) {
            return "-1";
        }
        int redundantChanges = k - changesNeeded;

        for (int index = 0; index < letters.length / 2; index++) {

            if (letters[index] != letters[letters.length - index - 1]) {
                if (redundantChanges > 0 && letters[index] != '9' && letters[letters.length - index - 1] != '9') {
                    letters[index] = '9';
                    letters[letters.length - index - 1] = '9';
                    redundantChanges--;
                } else {
                    char commonLetter = letters[index] > letters[letters.length - index - 1] ?
                            letters[index] : letters[letters.length - index - 1];
                    letters[index] = commonLetter;
                    letters[letters.length - index - 1] = commonLetter;
                }
            } else {
                if (redundantChanges > 1 && letters[index] != '9') {
                    letters[index] = '9';
                    letters[letters.length - index - 1] = '9';
                    redundantChanges -= 2;
                }
            }
        }
        if (letters.length % 2 == 1 && redundantChanges > 0) {
            letters[letters.length / 2] = '9';
        }
        return new String(letters);
    }
}