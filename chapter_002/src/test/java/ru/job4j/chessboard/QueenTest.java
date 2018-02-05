package ru.job4j.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueenTest {
    private Queen queen;

    @Before
    public void setQueen() {
        this.queen = new Queen(new QueenBehavior(), new Cell(5, 3));
    }

    @Test
    public void whenQueenMoveUpThenQueenMoveUp() {
        Cell[] expected = new Cell[] {
                new Cell(5, 2),
                new Cell(5, 1),
                new Cell(5, 0)
        };
        Cell[] result = null;
        try {
            result = this.queen.way(new Cell(5, 3), new Cell(5, 0));
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
    public void whenQueenMoveDownThenQueenMoveDown() {
        Cell[] expected = new Cell[] {
                new Cell(5, 4),
                new Cell(5, 5),
                new Cell(5, 6)
        };
        Cell[] result = null;
        try {
            result = this.queen.way(new Cell(5, 3), new Cell(5, 6));
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
    public void whenQueenMoveRightThenQueenMoveRight() {
        Cell[] expected = new Cell[] {
                new Cell(6, 3),
                new Cell(7, 3)
        };
        Cell[] result = null;
        try {
            result = this.queen.way(new Cell(5, 3), new Cell(7, 3));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
        assertThat(result[1].getX(), is(expected[1].getX()));
        assertThat(result[1].getY(), is(expected[1].getY()));
    }

    @Test
    public void whenQueenMoveLeftThenQueenMoveLeft() {
        Cell[] expected = new Cell[] {
                new Cell(4, 3),
                new Cell(3, 3),
                new Cell(2, 3)
        };
        Cell[] result = null;
        try {
            result = this.queen.way(new Cell(5, 3), new Cell(2, 3));
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
    public void whenQueenMoveUpAndLeftThenQueenMoveUpAndLeft() {
        Cell[] expected = new Cell[] {
                new Cell(4, 2),
                new Cell(3, 1),
                new Cell(2, 0)
        };
        Cell[] result = null;
        try {
            result = this.queen.way(new Cell(5, 3), new Cell(2, 0));
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
    public void whenQueenMoveUpAndRightThenQueenMoveUpAndRight() {
        Cell[] expected = new Cell[] {
                new Cell(6, 2),
                new Cell(7, 1)
        };
        Cell[] result = null;
        try {
            result = this.queen.way(new Cell(5, 3), new Cell(7, 1));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
        assertThat(result[1].getX(), is(expected[1].getX()));
        assertThat(result[1].getY(), is(expected[1].getY()));
    }

    @Test
    public void whenQueenMoveDownAndLeftThenQueenMoveDownAndLeft() {
        Cell[] expected = new Cell[] {
                new Cell(4, 4),
                new Cell(3, 5),
                new Cell(2, 6)
        };
        Cell[] result = null;
        try {
            result = this.queen.way(new Cell(5, 3), new Cell(2, 6));
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
    public void whenQueenMoveDownAndRightThenQueenMoveDownAndRight() {
        Cell[] expected = new Cell[] {
                new Cell(6, 4),
                new Cell(7, 5)
        };
        Cell[] result = null;
        try {
            result = this.queen.way(new Cell(5, 3), new Cell(7, 5));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
        assertThat(result[1].getX(), is(expected[1].getX()));
        assertThat(result[1].getY(), is(expected[1].getY()));
    }
}
