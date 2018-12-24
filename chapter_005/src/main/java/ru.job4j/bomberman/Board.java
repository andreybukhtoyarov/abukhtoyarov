package ru.job4j.bomberman;

import ru.job4j.chessboard.Cell;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Math.abs;

/**
 * This class describe game board.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Board {
    /**
     * Game board.
     */
    private final ReentrantLock[][] board;

    public Board(int column, int line) {
        this.board = new ReentrantLock[column][line];
        for (int indexLine = 0; indexLine < line; ++indexLine) {
            for (int indexColumn = 0; indexColumn < column; ++indexColumn) {
                this.board[indexLine][indexColumn] = new ReentrantLock();
            }
        }
    }

    /**
     * Move.
     * @param source Cell source.
     * @param dist Cell dist.
     * @return true if move is possible.
     */
    public boolean move(Cell source, Cell dist) {
        boolean canMove = false;
        if (getReentrantLock(dist).tryLock()) {
            if (within(dist) && checkWay(source, dist)) {
                getReentrantLock(source).unlock();
                canMove = true;
            } else {
                getReentrantLock(dist).unlock();
            }
        }
        return canMove;
    }

    /**
     * Return ReentrantLock by Cell.
     * @param cell Cell.
     * @return ReentrantLock.
     */
    public ReentrantLock getReentrantLock(Cell cell) {
        return board[cell.getX()][cell.getY()];
    }

    /**
     * Is the coordinate within the array.
     * @param dist coordinate
     * @return true if the coordinate within the array.
     */
    private boolean within(Cell dist) {
        boolean within = false;
        int x = dist.getX();
        int y = dist.getY();
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
            within = true;
        }
        return within;
    }

    /**
     * Checks if the difference between the old and the new coordinate is one
     * @param source Cell source.
     * @param dist Cell destination.
     * @return true if between is one.
     */
    private boolean checkWay(Cell source, Cell dist) {
        boolean check = false;
        int xStep = abs(abs(source.getX()) - abs(dist.getX()));
        int yStep = abs(abs(source.getY()) - abs(dist.getY()));
        if (xStep <= 1 && xStep >= 0 && yStep <= 1 && yStep >= 0) {
            check = true;
        }
        return check;
    }
}
