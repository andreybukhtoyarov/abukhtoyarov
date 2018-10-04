package ru.job4j.chessboard;

/**
 * This class describe behavior of chess figure the Rook.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class RookBehavior extends Position implements BehaviorFigure {

	@Override
    public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
		boolean canMove = false;
		int x = getPositionX(source, dest, (x1, x2) -> x1 - x2);
		int y = getPositionY(source, dest, (y1, y2) -> y1 - y2);
		if (y != 0 && source.getX() == dest.getX()
			||
				x != 0 && source.getY() == dest.getY()
			) {
				canMove = true;
			} else {
				throw new ImpossibleMoveException("Ладья ходит только по горизонтали или вертикали!");
			}
		return canMove;
	}
}
