package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @since 28.12.2017.
 * @version 1.0.
 */
public class BoardTest {
    @Test
    public void whenWidthThreeAndHeightThreeThenStringWithThreeColumnsAndThreeRows() {
        Board board = new Board();
        String ln = System.lineSeparator();

        assertThat(
                board.paint(3, 3),
                is(String.format("x x%s x %sx x%s", ln, ln, ln))
        );
    }

    @Test
    public void whenWidthFiveAndHeightFourThenStringWithFiveColumnsAndFourRows() {
        Board board = new Board();
        String ln = System.lineSeparator();

        assertThat(
                board.paint(5, 4),
                is(String.format("x x x%s x x %sx x x%s x x %s", ln, ln, ln, ln))
        );
    }
}
