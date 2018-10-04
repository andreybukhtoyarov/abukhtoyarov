package ru.job4j.chessboard;

/**
 * This class describe chess figure the Queen.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Queen extends Figure {
	private final Rook rook = new Rook(new RookBehavior(), this.position);
	private final Bishop bishop = new Bishop(new BishopBehavior(), this.position);
    
	public Queen(BehaviorFigure behaviorFigure, Cell position) {
        super(behaviorFigure, position);
    }

	@Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] way = null;
		if (this.behaviorFigure.canMove(source, dest) && p.getPositionX(source, dest, (x1, x2) -> x1 - x2) == 0
				|| p.getPositionY(source, dest, (y1, y2) -> y1 - y2) == 0) {
			way = rook.way(source, dest);
		} else if (this.behaviorFigure.canMove(source, dest)) {
			way = bishop.way(source, dest);
		}
		return way;
	}
	
	@Override
    Figure copy(Cell dest) {
        return new Queen(this.behaviorFigure, dest);
    }
}
