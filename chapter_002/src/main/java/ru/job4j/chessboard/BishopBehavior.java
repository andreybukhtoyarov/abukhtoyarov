package ru.job4j.chessboard;

public class BishopBehavior implements BehaviorFigure {
    @Override
    public boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean canMove = false;
        if (source.getX() - dest.getX() != 0
                && source.getY() - dest.getY() != 0
                && Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY())
                ) {
            canMove = true;
        } else {
            throw new ImpossibleMoveException("Слон ходит только по диагонали!");
        }
        return canMove;
    }
}
