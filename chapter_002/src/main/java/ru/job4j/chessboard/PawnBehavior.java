package ru.job4j.chessboard;

/**
 * This class describe behavior of chess figure the Pawn.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class PawnBehavior extends Position implements BehaviorFigure {

	@Override
	public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
		boolean canMove = false;
		int x = getPositionX(source, dest, (x1, x2) -> x1 - x2);
		int y = getPositionY(source, dest, (y1, y2) -> y1 - y2);
		if (y != 0 && source.getX() == dest.getX() && Math.abs(y) == 1) {
			canMove = true;
		} else {
			throw new ImpossibleMoveException("Пешка ходит только вперед!");
		}
		return canMove;
	}
}
