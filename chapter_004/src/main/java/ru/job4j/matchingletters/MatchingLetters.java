package ru.job4j.matchingletters;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains method matcherLetter.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class MatchingLetters {

    /**
     * Returns a Map where the key is a letter,
     * and the value is the number of matches of that letter in the word.
     * @param str word.
     * @return HashMap.
     */
    public Map<Character, Integer> matcherLetter(String str) {
        Map<Character, Integer> matches = new HashMap<>();
        char[] ch = str.toCharArray();
        for (char character : ch) {
            if (matches.containsKey(character)) {
                matches.put(character, matches.get(character) + 1);
            } else {
                matches.put(character, 1);
            }
        }
        return matches;
    }
}
