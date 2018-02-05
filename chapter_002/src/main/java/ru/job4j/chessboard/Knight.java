package ru.job4j.chessboard;

import static java.lang.Math.abs;
import static java.lang.Integer.compare;

/**
 * This class describe chess figure the Knight.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Knight extends Figure {
    
	public Knight(BehaviorFigure behaviorFigure, Cell position) {
        super(behaviorFigure, position);
    }

	@Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] way = null;
		
		if (this.behaviorFigure.canMove(source, dest)) {
			way = new Cell[1];

			if (abs(source.getX() - dest.getX()) == 2) {
				way[0] = new Cell(
						source.getX() + 2 * compare(dest.getX(), source.getX()),
						source.getY() + compare(dest.getY(), source.getY())
						);
			} else if (abs(source.getY() - dest.getY()) == 2) {
				way[0] = new Cell(
						source.getX() + compare(dest.getX(), source.getX()),
						source.getY() + 2 * compare(dest.getY(), source.getY())
				);
			}
		}
		return way;
	}
	
	@Override
    Figure copy(Cell dest) {
        return new Knight(this.behaviorFigure, dest);
    }
}
