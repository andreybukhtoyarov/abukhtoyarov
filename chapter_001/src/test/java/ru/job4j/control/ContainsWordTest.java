package ru.job4j.control;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class ContainsWordTest {
    @Test
    public void whenCocaColaAndColaThenTrue() {
        ContainsWord contWord = new ContainsWord();

        String firstWord = "coca-cola";
        String secondWord = "cola";

        boolean result = contWord.contains(firstWord, secondWord);
        boolean expected = true;

        assertThat(
                result,
                is(expected)
        );
    }

    @Test
    public void whenSunglassesAndColaThenFalse() {
        ContainsWord contWord = new ContainsWord();

        String firstWord = "sunglasses";
        String secondWord = "cola";

        boolean result = contWord.contains(firstWord, secondWord);
        boolean expected = false;

        assertThat(
                result,
                is(expected)
        );
    }

    @Test
    public void whenSunglassesAndSunThenTrue() {
        ContainsWord contWord = new ContainsWord();

        String firstWord = "sunglasses";
        String secondWord = "sun";

        boolean result = contWord.contains(firstWord, secondWord);
        boolean expected = true;

        assertThat(
                result,
                is(expected)
        );
    }

    @Test
    public void whenCocaColaAndCaCoThenTrue() {
        ContainsWord contWord = new ContainsWord();

        String firstWord = "coca-cola";
        String secondWord = "ca-co";

        boolean result = contWord.contains(firstWord, secondWord);
        boolean expected = true;

        assertThat(
                result,
                is(expected)
        );
    }

    @Test
    public void whenCocaColaAndBiggerWordThenFalse() {
        ContainsWord contWord = new ContainsWord();

        String firstWord = "coca-cola";
        String secondWord = "Panathinaikos";

        boolean result = contWord.contains(firstWord, secondWord);
        boolean expected = false;

        assertThat(
                result,
                is(expected)
        );
    }
}
