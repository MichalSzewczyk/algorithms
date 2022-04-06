package com.algo.interview.google.wordpredictor;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        WordPredictor predictor = new WordPredictor();
        predictor.train("first sentence goes here");
        predictor.train("first sentence goes there");
        predictor.train("first sentence appears there");
        predictor.train("first word appears there");
        predictor.train("second word appears there");

        String predicted = predictor.predict("first");

        System.out.println(predicted);
    }

    private static class WordPredictor {
        private final Map<String, String> mostFrequentWords;
        private final Map<String, Map<String, Integer>> wordCount;

        private WordPredictor() {
            mostFrequentWords = new HashMap<>();
            wordCount = new HashMap<>();
        }

        public void train(String sentence) {
            String[] words = sentence.split(" ");
            for (int idx = 1; idx < words.length; idx++) {
                train(words[idx - 1], words[idx]);
            }
        }

        private void train(String firstWord, String secondWord) {
            Map<String, Integer> counts = wordCount.computeIfAbsent(firstWord, __ -> new HashMap<>());
            int count = counts.getOrDefault(secondWord, 0);
            count++;
            counts.put(secondWord, count);
            String mostFrequentWord = mostFrequentWords.get(firstWord);
            int mostFrequentWordCount = counts.getOrDefault(mostFrequentWord, 0);
            if (count > mostFrequentWordCount) {
                mostFrequentWords.put(firstWord, secondWord);
            }
        }

        public String predict(String word) {
            return mostFrequentWords.getOrDefault(word, "");
        }
    }
}
