package ru.job4j.bomberman;

import ru.job4j.chessboard.Cell;

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
        ReentrantLock.Builder builder = new ReentrantLock.Builder();
        for (int indexLine = 0; indexLine < line; ++indexLine) {
            for (int indexColumn = 0; indexColumn < column; ++indexColumn) {
                this.board[indexLine][indexColumn] = builder.build();
            }
        }
    }

    /**
     * Move.
     * @param source Cell source.
     * @param dist Cell dist.
     * @return true if move.
     */
    public boolean move(Cell source, Cell dist) {
        boolean canMove = false;
        ReentrantLock figure = board[dist.getX()][dist.getY()];
        synchronized (board[dist.getX()][dist.getY()]) {
            if (within(dist) && !figure.isLocked()) {
                canMove = true;
                figure.setLocked(true);
            }
        }
        return canMove;
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
}
