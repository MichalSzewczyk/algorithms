package com.algo.biggerisgreater;

import java.util.*;

public class Algorithm {
    /**
     Given a word, create a new word by swapping some or all of its characters. This new word must meet two criteria:
        - It must be greater than the original word
        - It must be the smallest word that meets the first condition
     For example, given the word w=abcd, the next largest word is abdc
     */
    static String biggerIsGreater(String w) {
        char[] word = w.toCharArray();
        TreeSet<Character> characters = new TreeSet<>();
        List<Character> allCharacters = new ArrayList<>();
        for (int index = word.length - 1; index >= 0; index--) {
            Character lowestUpperLetter = characters.ceiling((char) (word[index] + 1));
            if (Objects.isNull(lowestUpperLetter)) {
                characters.add(word[index]);
                allCharacters.add(word[index]);
            } else {
                allCharacters.remove(lowestUpperLetter);
                allCharacters.add(word[index]);
                word[index] = lowestUpperLetter;
                Collections.sort(allCharacters);
                while (++index < word.length) {
                    Character lowestLetter = allCharacters.remove(0);
                    word[index] = lowestLetter;
                }
                return new String(word);
            }
        }
        return "no answer";
    }
}
