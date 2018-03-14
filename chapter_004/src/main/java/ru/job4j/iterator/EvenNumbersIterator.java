package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is iterator for a even numbers.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class EvenNumbersIterator implements Iterator {
    /**Field with array.*/
    private final int[] values;
    /**Current index.*/
    private int index = 0;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    /**
     * Return index of even number in array.
     * @return {@code -1} if there are no more items
     */
    private int getIndex() {
        int result = -1;
        for (int iteration = this.index; iteration < this.values.length; ++iteration) {
            if (this.values[iteration] % 2 == 0) {
                result = iteration;
                break;
            }
        }
        return result;
    }

    /**
     * Looks if there is a next item.
     * @return if the following element exists - return true.
     */
    @Override
    public boolean hasNext() {
        boolean hasNext = true;
        if (this.values.length == 0 || getIndex() < 0) {
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
        this.index = getIndex();
        return this.values[this.index++];
    }
}
