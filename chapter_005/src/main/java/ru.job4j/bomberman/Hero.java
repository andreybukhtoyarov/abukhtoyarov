package ru.job4j.bomberman;

import javafx.scene.shape.Circle;
import ru.job4j.chessboard.Cell;

/**
 * This class describe a game Hero.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Hero {
    /**
     * link to game board.
     */
    private final Board board;
    /**
     * Cell of current position.
     */
    private Cell source;
    /**
     * Shape of hero.
     */
    private final Circle heroShape;

    public Hero(Board board, Cell source, Circle heroShape) {
        this.board = board;
        this.source = source;
        this.heroShape = heroShape;
    }

    /**
     * Move hero to left.
     */
    public void moveLeft() {
        if (this.board.move(source, new Cell(source.getX() - 1, source.getY()))) {
            heroShape.setCenterX(source.getX() - 1);
            heroShape.setCenterY(source.getY());
            source = new Cell(source.getX() - 1, source.getY());
        }
    }

    /**
     * Move hero to Right.
     */
    public void moveRight() {
        if (this.board.move(source, new Cell(source.getX() + 1, source.getY()))) {
            heroShape.setCenterX(source.getX() + 1);
            heroShape.setCenterY(source.getY());
            source = new Cell(source.getX() + 1, source.getY());
        }
    }

    /**
     * Move hero to Up.
     */
    public void moveUp() {
        if (this.board.move(source, new Cell(source.getX(), source.getY() + 1))) {
            heroShape.setCenterX(source.getX());
            heroShape.setCenterY(source.getY() + 1);
            source = new Cell(source.getX(), source.getY() + 1);
        }
    }

    /**
     * Move hero to Down.
     */
    public void moveDown() {
        if (this.board.move(source, new Cell(source.getX(), source.getY() - 1))) {
            heroShape.setCenterX(source.getX());
            heroShape.setCenterY(source.getY() - 1);
            source = new Cell(source.getX(), source.getY() - 1);
        }
    }
}
