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
     * If number is prime return true.
     * @return if number is prime return true.
     */
    private boolean isPrime(int number) {
        boolean isPrime = false;
        if (number == 2 || number == 3) {
            isPrime = true;
        } else {
            for (int divider = 2; divider <= Math.sqrt(number); ++divider) {
                if (number % divider == 0) {
                    break;
                } else if (divider == (int) Math.sqrt(number)) {
                    isPrime = true;
                }
            }
        }
        return isPrime;
    }

    /**
     * Looks if there is a next item.
     * @return if the following element exists - return true.
     */
    @Override
    public boolean hasNext() {
        boolean hasNext = false;
        for (int index = this.index; index < this.numbers.length; ++index) {
            if (isPrime(this.numbers[index])) {
                hasNext = true;
                this.index = index;
                break;
            }
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
        return this.numbers[this.index++];
    }
}
