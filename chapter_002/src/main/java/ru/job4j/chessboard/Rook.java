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
			if (source.getX() - dest.getX() != 0) {
				way = new Cell[abs(source.getX() - dest.getX())];
				for (int step = 0; step < way.length; ++step) {
					way[step] = new Cell(
							source.getX()
									+ step * compare(dest.getX(), source.getX())
									+ compare(dest.getX(), source.getX()),
							source.getY()
					);
				}
			} else if (source.getY() - dest.getY() != 0) {
				way = new Cell[abs(source.getY() - dest.getY())];
				for (int step = 0; step < way.length; ++step) {
					way[step] = new Cell(
							source.getX(),
							source.getY()
									+ step * compare(dest.getY(), source.getY())
									+ compare(dest.getY(), source.getY())
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
