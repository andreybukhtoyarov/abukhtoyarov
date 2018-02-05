package ru.job4j.chessboard;

/**
 * This class describe behavior of chess figure the Queen.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class QueenBehavior implements BehaviorFigure {
	private final BehaviorFigure behaviorRook = new RookBehavior();
	private final BehaviorFigure behaviorBishop = new BishopBehavior();

	@Override
    public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
		boolean canMove = false;

		try {
			if (behaviorRook.canMove(source, dest)) {
				canMove = true;
			}
		} catch (Exception e) {
			if (behaviorBishop.canMove(source, dest)) {
				canMove = true;
			}
		}
		return canMove;
	}
}
