package ru.job4j.chessboard;

import static java.lang.Math.abs;

/**
 * This class describe behavior of chess figure the Bishop.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class BishopBehavior implements BehaviorFigure {
    @Override
    public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean canMove = false;
        if (source.getX() - dest.getX() != 0
                && source.getY() - dest.getY() != 0
                && abs(source.getX() - dest.getX()) == abs(source.getY() - dest.getY())
                ) {
            canMove = true;
        } else {
            throw new ImpossibleMoveException("Слон ходит только по диагонали!");
        }
        return canMove;
    }
}
