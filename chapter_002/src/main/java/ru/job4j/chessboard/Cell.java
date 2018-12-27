package ru.job4j.chessboard;

import java.util.Objects;

/**
 * This class describe coordinates.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Cell {
    /**Field with abscissa coordinate.*/
    private int x;
    /**Field with ordinate coordinate.*/
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x.
     * @return x.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y.
     * @return y.
     */
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
