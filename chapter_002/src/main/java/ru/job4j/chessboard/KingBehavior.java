package ru.job4j.chessboard;

import static java.lang.Math.abs;

/**
 * This class describe behavior of chess figure the King.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class KingBehavior implements BehaviorFigure {
	@Override
	public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
		boolean canMove = false;
		if (abs(source.getX() - dest.getX()) == 0 && abs(source.getY() - dest.getY()) == 1
				||
				abs(source.getY() - dest.getY()) == 0 && abs(source.getX() - dest.getX()) == 1
				||
				abs(source.getX() - dest.getX()) == abs(source.getY() - dest.getY())
						&& abs(source.getX() - dest.getX()) == 1
				) {
			canMove = true;
		} else {
			throw new ImpossibleMoveException("Король так не ходит!");
		}
		return canMove;
	}
}
