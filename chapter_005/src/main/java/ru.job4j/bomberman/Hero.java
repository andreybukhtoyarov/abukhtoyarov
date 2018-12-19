package ru.job4j.bomberman;

import ru.job4j.chessboard.Cell;

/**
 * This class describe game Hero.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Hero implements Runnable {
    /**
     * link to game board.
     */
    private final Board board;
    /**
     * Cell of current position.
     */
    private Cell source;

    public Hero(Board board, Cell source) {
        this.board = board;
        this.source = source;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int stepX = 1;
            int stepY = 0;
            if (board.move(source, new Cell(source.getX() + stepX, source.getY() + stepY))) {
                source = new Cell(source.getX() + stepX, source.getY() + stepY);
            } else {
                stepX = stepX * -1;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
