package ru.job4j.chessboard;

import static java.lang.Integer.compare;

/**
 * This class describe chess figure the Pawn.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Pawn extends Figure {

	public Pawn(BehaviorFigure behaviorFigure, Cell position) {
		super(behaviorFigure, position);
	}

	@Override
	Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
		Cell[] way = null;

		if (this.behaviorFigure.canMove(source, dest)) {
			way = new Cell[Math.abs(source.getY() - dest.getY())];

			for (int step = 0; step < way.length; ++step) {
				way[step] = new Cell(
						source.getX(),
						source.getY()
								+ step * compare(dest.getY(), source.getY())
								+ compare(dest.getY(), source.getY()));
			}
		}
		return way;
	}

	@Override
	Figure copy(Cell dest) {
		return new Pawn(this.behaviorFigure, dest);
	}
}
