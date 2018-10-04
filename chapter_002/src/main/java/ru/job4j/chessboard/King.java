package ru.job4j.chessboard;

import static java.lang.Integer.compare;

/**
 * This class describe chess figure the King.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class King extends Figure {

	public King(BehaviorFigure behaviorFigure, Cell position) {
		super(behaviorFigure, position);
	}

	@Override
	Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
		Cell[] way = null;
		if (this.behaviorFigure.canMove(source, dest)) {
			way = new Cell[]{
					new Cell(
							source.getX() + p.getPositionX(source, dest, (x1, x2) -> compare(x2, x1)),
							source.getY() + p.getPositionY(source, dest, (y1, y2) -> compare(y2, y1))
					)
			};
		}
		return way;
	}

	@Override
	Figure copy(Cell dest) {
		return new King(this.behaviorFigure, dest);
	}
}
