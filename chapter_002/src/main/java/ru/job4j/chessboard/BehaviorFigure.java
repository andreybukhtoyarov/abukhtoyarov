package ru.job4j.chessboard;

/**
 * This interface for method which is a behavior chess figure.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface BehaviorFigure {
    /**
     * This method determines can chess figure move along a given coordinate?
     * @param source start coordinate.
     * @param dest end coordinate.
     * @return the ability to move along a given coordinate.
     * @throws ImpossibleMoveException
     */
    boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException;
}
