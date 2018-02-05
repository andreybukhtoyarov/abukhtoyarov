package ru.job4j.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PawnTest {
    private Pawn pawn;

    @Before
    public void setPawn() {
        this.pawn = new Pawn(new PawnBehavior(), new Cell(5, 3));
    }

    @Test
    public void whenPawnMoveDownThenPawnMoveDown() {
        Cell[] expected = new Cell[] {
                new Cell(5, 4)
        };
        Cell[] result = null;
        try {
            result = this.pawn.way(new Cell(5, 3), new Cell(5, 4));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }

    @Test
    public void whenPawnMoveUpThenPawnMoveUp() {
        Cell[] expected = new Cell[] {
                new Cell(5, 2)
        };
        Cell[] result = null;
        try {
            result = this.pawn.way(new Cell(5, 3), new Cell(5, 2));
        } catch (Exception e) {
            e.getMessage();
        }
        assertThat(result[0].getX(), is(expected[0].getX()));
        assertThat(result[0].getY(), is(expected[0].getY()));
    }
}
