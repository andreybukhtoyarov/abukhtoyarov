package ru.job4j.chessboard;

import static java.lang.Math.abs;

/**
 * This class describe behavior of chess figure the King.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class KingBehavior extends Position implements BehaviorFigure {

    @Override
    public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean canMove = false;
        int x = getPositionX(source, dest, (x1, x2) -> x1 - x2);
        int y = getPositionY(source, dest, (y1, y2) -> y1 - y2);
        if (abs(x) == 0 && abs(y) == 1
                ||
                abs(y) == 0 && abs(x) == 1
                ||
                abs(x) == abs(y) && abs(x) == 1
                ) {
            canMove = true;
        } else {
            throw new ImpossibleMoveException("Король так не ходит!");
        }
        return canMove;
    }
}
