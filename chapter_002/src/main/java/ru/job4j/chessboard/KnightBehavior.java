package ru.job4j.chessboard;

import static java.lang.Math.abs;

/**
 * This class describe behavior of chess figure the Knight.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class KnightBehavior implements BehaviorFigure {
	@Override
    public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
		boolean canMove = false;
		if (abs(source.getX() - dest.getX()) == 2 && abs(source.getY() - dest.getY()) == 1
			||
				abs(source.getY() - dest.getY()) == 2 && abs(source.getX() - dest.getX()) == 1
			) {
				canMove = true;
			} else {
				throw new ImpossibleMoveException("Конь так не ходит!");
			}
		return canMove;
	}
}
