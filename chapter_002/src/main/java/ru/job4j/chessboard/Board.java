package ru.job4j.chessboard;

public class Board {

    Figure[][] figures = new Figure[8][8];

    public Figure add(Figure figure) {
        this.figures[figure.position.getX()][figure.position.getY()] = figure;
        return figure;
    }

    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean move = false;
        Figure figure = this.figures[source.getX()][source.getY()];
        if (figure == null) {
            throw new FigureNotFoundException("Тут нет фигуры!");
        } else if (figure.behaviorFigure.canMove(source, dest)) {
            Cell[] way = figure.way(source, dest);
            for (int step = 0; step < way.length; ++step) {
                if (this.figures[way[step].getX()][way[step].getY()] != null) {
                    throw new OccupiedWayException("На пути стоит фигура, нужно выбрать другой путь!");
                } else {
                    move = true;
                    this.figures[dest.getX()][dest.getY()] = figure.copy(dest);
                }
            }
        }
        return move;
    }

}
