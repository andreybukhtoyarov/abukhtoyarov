package ru.job4j.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KingTest {
    private King king;

    @Before
    public void kingSet() {
        this.king = new King(new KingBehavior(), new Cell(5, 3));
    }

    @Test
    public void whenKingMoveDownThenMoveDown() {
        Cell[] expected = new Cell[] {
                new Cell(5, 4)
        };
        Cell[] result = null;
        try {
            result = this.king.way(new Cell(5, 3), new Cell(5, 4));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenKingMoveUpThenMoveUp() {
        Cell[] expected = new Cell[] {
                new Cell(5, 2),
        };
        Cell[] result = null;
        try {
            result = this.king.way(new Cell(5, 3), new Cell(5, 2));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenKingMoveUpAndRightThenMove() {
        Cell[] expected = new Cell[] {
                new Cell(6, 2),
        };
        Cell[] result = null;
        try {
            result = this.king.way(new Cell(5, 3), new Cell(6, 2));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }
}
