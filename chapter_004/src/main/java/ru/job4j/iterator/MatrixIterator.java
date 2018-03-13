package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is iterator for a two-dimensional array.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class MatrixIterator implements Iterator {
    /**Field with array.*/
    private final int[][] values;
    /**Current depth.*/
    private int deep = 0;
    /**Current index.*/
    private int index = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    /**
     * Looks if there is a next item.
     * @return If the following element exists - return true.
     */
    @Override
    public boolean hasNext() {
        boolean hasNext = true;
        if (this.values.length == 0 || this.deep == this.values.length - 1 && this.index == this.values[this.deep].length) {
            hasNext = false;
        }
        return hasNext;
    }

    /**
     * Return the next element.
     * @return the next element.
     */
    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.index >= this.values[this.deep].length) {
            ++this.deep;
            this.index = 0;
        }
        return this.values[deep][index++];
    }
}
