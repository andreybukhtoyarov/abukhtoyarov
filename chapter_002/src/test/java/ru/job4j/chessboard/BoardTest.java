package ru.job4j.chessboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.core.IsNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Board board;

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(out));
    }

    @Before
    public void setBoard() {
        this.board = new Board();
        this.board.add(new Bishop(new BishopBehavior(), new Cell(2, 0)));
    }

    @After
    public void backStdOut() {
        System.setOut(stdOut);
    }

    @Test
    public void whenMoveNotExistFigureThenFigureNotFoundException() {
        try {
            board.move(new Cell(1, 1), new Cell(2, 2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertThat(out.toString(), is("Тут нет фигуры!\n"));
        assertThat(this.board.figures[2][2], new IsNull());
    }

    @Test
    public void whenMoveFigureNotByBehaviorThenImpossibleMoveException() {
        try {
            board.move(new Cell(2, 0), new Cell(5, 5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertThat(out.toString(), is("Слон ходит только по диагонали!\n"));
        assertThat(this.board.figures[5][5], new IsNull());
    }

    @Test
    public void whenMoveFigureToOccupiedWayThenOccupiedWayException() {
        this.board.add(new Bishop(new BishopBehavior(), new Cell(3, 1)));
        try {
            board.move(new Cell(2, 0), new Cell(5, 3));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertThat(out.toString(), is("На пути стоит фигура, нужно выбрать другой путь!\n"));
        assertThat(this.board.figures[5][3], new IsNull());
    }
}
