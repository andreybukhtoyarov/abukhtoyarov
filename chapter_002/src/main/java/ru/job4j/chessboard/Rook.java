package ru.job4j.chessboard;

import static java.lang.Integer.compare;
import static java.lang.Math.abs;

/**
 * This class describe chess figure the Rook.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Rook extends Figure {
    
	public Rook(BehaviorFigure behaviorFigure, Cell position) {
        super(behaviorFigure, position);
    }

	@Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] way = null;
		if (this.behaviorFigure.canMove(source, dest)) {
			if (p.getPositionX(source, dest, (x1, x2) -> x1 - x2) != 0) {
				way = new Cell[p.getPositionX(source, dest, (x1, x2) -> abs(x1 - x2))];
				for (int step = 0; step < way.length; ++step) {
					way[step] = new Cell(
							source.getX()
									+ step * p.getPositionX(source, dest, (x1, x2) -> compare(x2, x1))
									+ p.getPositionX(source, dest, (x1, x2) -> compare(x2, x1)),
							source.getY()
					);
				}
			} else if (p.getPositionY(source, dest, (y1, y2) -> y1 - y2) != 0) {
				way = new Cell[p.getPositionY(source, dest, (y1, y2) -> abs(y1 - y2))];
				for (int step = 0; step < way.length; ++step) {
					way[step] = new Cell(
							source.getX(),
							source.getY()
									+ step * p.getPositionY(source, dest, (y1, y2) -> compare(y2, y1))
									+ p.getPositionY(source, dest, (y1, y2) -> compare(y2, y1))
					);
				}
			}
		}
		return way;
	}
	
	@Override
    Figure copy(Cell dest) {
        return new Rook(this.behaviorFigure, dest);
    }
}
