package ru.job4j.bomberman;

import javafx.scene.shape.Circle;
import ru.job4j.chessboard.Cell;

import java.util.Random;

/**
 * This class describe a game monster.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Monster implements Runnable {
    /**
     * link to game board.
     */
    private final Board board;
    /**
     * Cell of current position.
     */
    private Cell source;
    /**
     * Monster figure.
     */
    private final Circle circle;

    public Monster(Board board, Cell source, Circle circle) {
        this.board = board;
        this.source = source;
        this.circle = circle;
    }

    @Override
    public void run() {
        int stepX = randomStep();
        int stepY = randomStep();
        int countSameStep = 0;
        while (!Thread.currentThread().isInterrupted()) {
            Cell dest = new Cell(source.getX() + stepX, source.getY() + stepY);
            if (board.move(source, dest) && countSameStep < 5) {
                source = dest;
                circle.setCenterX(source.getX() * 20);
                circle.setCenterY(source.getY() * 20);
                ++countSameStep;
                System.out.printf("%s  x - %s, y - %s\n", Thread.currentThread().getName(), source.getX(), source.getY());
            } else {
                countSameStep = 0;
                System.out.printf("%s - %s\n", Thread.currentThread().getName(), "Bad way");
                stepX = randomStep();
                stepY = randomStep();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Get random int [-1;1].
     * @return random int [-1;1].
     */
    private int randomStep() {
        return new Random().nextInt(3) - 1;
    }
}
