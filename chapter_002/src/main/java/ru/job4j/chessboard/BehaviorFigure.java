package ru.job4j.chessboard;

public interface BehaviorFigure {
    boolean canMove(Cell source, Cell dest) throws ImpossibleMoveException;
}
