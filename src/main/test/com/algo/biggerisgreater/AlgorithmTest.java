package com.algo.biggerisgreater;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AlgorithmTest {
    @ParameterizedTest
    @CsvSource(value = {"ab:ba", "bb:no answer", "hefg:hegf", "dhck:dhkc", "dkhc:hcdk", "fedcbabcd:fedcbabdc", "lmno:lmon",
            "zedawdvyyfumwpupuinbdbfndyehircmylbaowuptgmw:zedawdvyyfumwpupuinbdbfndyehircmylbaowuptgwm"}, delimiter = ':')
    public void shouldGenerateSmallestBiggerWord(String inputWord, String expectedWord) {
        String smallestBiggerWord = Algorithm.biggerIsGreater(inputWord);

        assertThat(smallestBiggerWord, is(expectedWord));
    }
}