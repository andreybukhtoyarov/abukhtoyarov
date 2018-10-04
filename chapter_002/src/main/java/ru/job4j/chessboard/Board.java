package ru.job4j.chessboard;

import java.util.function.Function;

/**
 * This class describe chess board.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Board {
    /**Field array of chess figures.*/
    Figure[][] figures = new Figure[8][8];

    /**
     * Add chess figure in array of chess figures.
     * @param figure chess figure.
     * @return chess figure.
     */
    public Figure add(Figure figure) {
        this.figures[figure.position.getX()][figure.position.getY()] = figure;
        return figure;
    }

    private Figure getFigure(Cell cell, Function<Figure, Figure> funk) {
        return funk.apply(this.figures[cell.getX()][cell.getY()]);
    }

    /**
     * Moves chess figures.
     * @param source start coordinate.
     * @param dest end coordinate.
     * @return the ability to move along a given coordinate.
     * @throws ImpossibleMoveException
     * @throws OccupiedWayException
     * @throws FigureNotFoundException
     */
    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure figure = getFigure(source, (x) -> x);
        if (figure == null) {
            throw new FigureNotFoundException("Тут нет фигуры!");
        }
        if (figure.behaviorFigure.canMove(source, dest)) {
            Cell[] way = figure.way(source, dest);
            for (Cell aWay : way) {
                if (getFigure(aWay, (x) -> x) != null) {
                    throw new OccupiedWayException("На пути стоит фигура, нужно выбрать другой путь!");
                }
            }
        }
        this.figures[dest.getX()][dest.getY()] = figure.copy(dest);
        this.figures[source.getX()][source.getY()] = null;
        return true;
    }

}
