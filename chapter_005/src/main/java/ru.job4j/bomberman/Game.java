package ru.job4j.bomberman;

import ru.job4j.chessboard.Cell;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is game.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Game {
    /**
     * Game board.
     */
    private Board board;
    /**
     * Pool Threads.
     */
    private final ExecutorService pool;

    public Game() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Add hero to Threads pool.
     */
    private void addHeroes() {
        pool.submit(new Hero(this.board, new Cell(0, 0)));
        pool.submit(new Hero(this.board, new Cell(8, 0)));
    }

    /**
     * Create new board and add heroes.
     * @param column column.
     * @param line line.
     */
    private void createGame(int column, int line) {
        board = new Board(column, line);
        addHeroes();
    }

    public static void main(String[] args) {
        new Game().createGame(9, 9);
    }
}
