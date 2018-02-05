package ru.job4j.chessboard;

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
}
