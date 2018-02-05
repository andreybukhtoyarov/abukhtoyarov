package ru.job4j.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KnightTest {
    private Knight knight;

    @Before
    public void setKing() {
        this.knight = new Knight(new KnightBehavior(), new Cell(5, 3));
    }

    @Test
    public void whenKnightMoveDownAndRightThenMoveDownAndRight() {
        Cell[] expected = new Cell[] {
                new Cell(6, 5)
        };
        Cell[] result = null;
        try {
            result = this.knight.way(new Cell(5, 3), new Cell(6, 5));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenKnightMoveDownAndLeftThenMoveDownAndLeft() {
        Cell[] expected = new Cell[] {
                new Cell(4, 5)
        };
        Cell[] result = null;
        try {
            result = this.knight.way(new Cell(5, 3), new Cell(4, 5));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenKnightMoveUpAndLeftThenMoveUpAndLeft() {
        Cell[] expected = new Cell[] {
                new Cell(4, 1)
        };
        Cell[] result = null;
        try {
            result = this.knight.way(new Cell(5, 3), new Cell(4, 1));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenKnightMoveUpAndRightThenMoveUpAndRight() {
        Cell[] expected = new Cell[] {
                new Cell(6, 1)
        };
        Cell[] result = null;
        try {
            result = this.knight.way(new Cell(5, 3), new Cell(6, 1));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenKnightMoveLeftAndDownThenMoveLeftAndDown() {
        Cell[] expected = new Cell[] {
                new Cell(3, 4)
        };
        Cell[] result = null;
        try {
            result = this.knight.way(new Cell(5, 3), new Cell(3, 4));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenKnightMoveLeftAndUpThenMoveLeftAndUp() {
        Cell[] expected = new Cell[] {
                new Cell(3, 2)
        };
        Cell[] result = null;
        try {
            result = this.knight.way(new Cell(5, 3), new Cell(3, 2));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenKnightMoveRightAndUpThenMoveRightAndUp() {
        Cell[] expected = new Cell[] {
                new Cell(7, 2)
        };
        Cell[] result = null;
        try {
            result = this.knight.way(new Cell(5, 3), new Cell(7, 2));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenKnightMoveRightAndDownThenMoveRightAndDown() {
        Cell[] expected = new Cell[] {
                new Cell(7, 4)
        };
        Cell[] result = null;
        try {
            result = this.knight.way(new Cell(5, 3), new Cell(7, 4));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }
}
