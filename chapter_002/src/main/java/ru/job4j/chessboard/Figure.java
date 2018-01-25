package ru.job4j.chessboard;

public abstract class Figure {
    final BehaviorFigure behaviorFigure;
    final Cell position;

    public Figure(BehaviorFigure behaviorFigure, Cell position) {
        this.behaviorFigure = behaviorFigure;
        this.position = position;
    }

    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    abstract Figure copy(Cell dest);
}
