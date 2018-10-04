package ru.job4j.chessboard;

import static java.lang.Integer.compare;

/**
 * This class describe chess figure the Bishop.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Bishop extends Figure {

    public Bishop(BehaviorFigure behaviorFigure, Cell position) {
        super(behaviorFigure, position);
    }

    @Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] way = null;
        int x = p.getPositionX(source, dest, (x1, x2) -> compare(x2, x1));
        int y = p.getPositionY(source, dest, (y1, y2) -> compare(y2, y1));
        if (this.behaviorFigure.canMove(source, dest)) {
            way = new Cell[Math.abs(p.getPositionX(source, dest, (x1, x2) -> x1 - x2))];
            for (int step = 0; step < way.length; ++step) {
                way[step] = new Cell(
                        source.getX() + step * x + x,
                        source.getY() + step * y + y
                );
            }
        }
        return way;
    }

    @Override
    Figure copy(Cell dest) {
        return new Bishop(this.behaviorFigure, dest);
    }

    @Override
    public String toString() {
        return "I'm Bishop";
    }
}
