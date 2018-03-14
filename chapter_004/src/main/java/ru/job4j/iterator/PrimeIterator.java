package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
    /**Field with array.*/
    private final int[] numbers;
    /**Current index.*/
    private int index = 0;

    public PrimeIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Return index of even number in array.
     * @return {@code -1} if there are no more items
     */
    private int getIndex() {
        int result = -1;
        outer:
        for (int iteration = this.index; iteration < this.numbers.length; ++iteration) {
            if (this.numbers[iteration] == 2 || this.numbers[iteration] == 3) {
                result = iteration;
                break;
            }
            for (int divider = 2; divider <= Math.sqrt(this.numbers[iteration]); ++divider) {
                if (this.numbers[iteration] % divider == 0) {
                    break;
                } else if (divider == (int) Math.sqrt(this.numbers[iteration])) {
                    result = iteration;
                    break outer;
                }
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
        if (this.numbers.length == 0 || getIndex() < 0) {
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
        return this.numbers[this.index++];
    }
}
