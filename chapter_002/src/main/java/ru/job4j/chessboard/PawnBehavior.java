package ru.job4j.chessboard;

/**
 * This class describe behavior of chess figure the Pawn.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class PawnBehavior implements BehaviorFigure {
	@Override
	public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
		boolean canMove = false;

		if (source.getY() - dest.getY() != 0
				&& source.getX() == dest.getX()
				&& Math.abs(source.getY() - dest.getY()) == 1
				) {
			canMove = true;
		} else {
			throw new ImpossibleMoveException("Пешка ходит только вперед!");
		}
		return canMove;
	}
}
