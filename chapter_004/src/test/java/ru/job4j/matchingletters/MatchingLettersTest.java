package ru.job4j.matchingletters;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatchingLettersTest {

    private MatchingLetters ml;
    private final String str = "nvjwcldnwsgjcc";

    @Before
    public void setMl() {
        this.ml = new MatchingLetters();
    }

    @Test
    public void test() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('n', 2);
        expected.put('v', 1);
        expected.put('j', 2);
        expected.put('w', 2);
        expected.put('c', 3);
        expected.put('l', 1);
        expected.put('d', 1);
        expected.put('s', 1);
        expected.put('g', 1);
        assertThat(ml.matcherLetter(str), is(expected));
    }
}