package ru.job4j.chessboard;

public class Bishop extends Figure {

    public Bishop(BehaviorFigure behaviorFigure, Cell position) {
        super(behaviorFigure, position);
    }

    @Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] way = null;

        int differenceX = source.getX() - dest.getX();
        int differenceY = source.getY() - dest.getY();

        if (this.behaviorFigure.canMove(source, dest)) {
            way = new Cell[Math.abs(differenceX)];
            if (differenceX < 0 && differenceY < 0) {
                for (int step = 0; step < Math.abs(differenceX); ++step) {
                    way[step] = new Cell(source.getX() + step + 1, source.getY() + step + 1);
                }
            } else if (differenceX > 0 && differenceY > 0) {
                for (int step = 0; step < Math.abs(differenceX); ++step) {
                    way[step] = new Cell(source.getX() - step - 1, source.getY() - step - 1);
                }
            } else if (differenceX < 0 && differenceY > 0) {
                for (int step = 0; step < Math.abs(differenceX); ++step) {
                    way[step] = new Cell(source.getX() + step + 1, source.getY() - step - 1);
                }
            } else if (differenceX > 0 && differenceY < 0) {
                for (int step = 0; step < Math.abs(differenceX); ++step) {
                    way[step] = new Cell(source.getX() - step - 1, source.getY() + step + 1);
                }
            }
        }
        return way;
    }

    @Override
    Figure copy(Cell dest) {
        return new Bishop(this.behaviorFigure, dest);
    }
}
