package ru.job4j.chessboard;

/**
 * This class describe behavior of chess figure the Rook.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class RookBehavior implements BehaviorFigure {

	@Override
    public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
		boolean canMove = false;
		if (source.getY() - dest.getY() != 0 && source.getX() == dest.getX()
			||
				source.getX() - dest.getX() != 0 && source.getY() == dest.getY()
			) {
				canMove = true;
			} else {
				throw new ImpossibleMoveException("Ладья ходит только по горизонтали или вертикали!");
			}
		return canMove;
	}
}
