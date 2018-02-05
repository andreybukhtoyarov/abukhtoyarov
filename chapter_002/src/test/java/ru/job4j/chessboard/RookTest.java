package ru.job4j.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RookTest {
    private Rook rook;

    @Before
    public void setRook() {
        this.rook = new Rook(new RookBehavior(), new Cell(5, 3));
    }

    @Test
    public void whenRookMoveUpThenRookMoveUp() {
        Cell[] expected = new Cell[] {
                new Cell(5, 2),
                new Cell(5, 1),
                new Cell(5, 0)
        };
        Cell[] result = null;
        try {
            result = this.rook.way(new Cell(5, 3), new Cell(5, 0));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
        assertThat(result[1].getX(), is(expected[1].getX()));
        assertThat(result[1].getY(), is(expected[1].getY()));
        assertThat(result[2].getX(), is(expected[2].getX()));
        assertThat(result[2].getY(), is(expected[2].getY()));
    }

    @Test
    public void whenRookMoveDownThenRookMoveDown() {
        Cell[] expected = new Cell[] {
                new Cell(5, 4),
                new Cell(5, 5),
                new Cell(5, 6)
        };
        Cell[] result = null;
        try {
            result = this.rook.way(new Cell(5, 3), new Cell(5, 6));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
        assertThat(result[1].getX(), is(expected[1].getX()));
        assertThat(result[1].getY(), is(expected[1].getY()));
        assertThat(result[2].getX(), is(expected[2].getX()));
        assertThat(result[2].getY(), is(expected[2].getY()));
    }

    @Test
    public void whenRookMoveLeftThenRookMoveLeft() {
        Cell[] expected = new Cell[] {
                new Cell(4, 3),
                new Cell(3, 3),
                new Cell(2, 3)
        };
        Cell[] result = null;
        try {
            result = this.rook.way(new Cell(5, 3), new Cell(2, 3));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
        assertThat(result[1].getX(), is(expected[1].getX()));
        assertThat(result[1].getY(), is(expected[1].getY()));
        assertThat(result[2].getX(), is(expected[2].getX()));
        assertThat(result[2].getY(), is(expected[2].getY()));
    }

    @Test
    public void whenRookMoveRightThenRookMoveRight() {
        Cell[] expected = new Cell[] {
                new Cell(6, 3),
                new Cell(7, 3)
        };
        Cell[] result = null;
        try {
            result = this.rook.way(new Cell(5, 3), new Cell(7, 3));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
        assertThat(result[1].getX(), is(expected[1].getX()));
        assertThat(result[1].getY(), is(expected[1].getY()));
    }
}
