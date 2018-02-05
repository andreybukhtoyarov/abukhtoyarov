package ru.job4j.chessboard;

/**
 * This class describe common features and behavior of chess figure.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public abstract class Figure {
    /**Field with behavior chess figure*/
    final BehaviorFigure behaviorFigure;
    /**Field with coordinate of chess figure*/
    final Cell position;

    Figure(BehaviorFigure behaviorFigure, Cell position) {
        this.behaviorFigure = behaviorFigure;
        this.position = position;
    }

    /**
     * This method make an array of coordinates of the patch a chess figure.
     * @param source start coordinate.
     * @param dest end coordinate.
     * @return array of coordinates.
     * @throws ImpossibleMoveException
     */
    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * This method make new chess figure with the given coordinate.
     * @param dest new coordinate.
     * @return new chess figure.
     */
    abstract Figure copy(Cell dest);
}
