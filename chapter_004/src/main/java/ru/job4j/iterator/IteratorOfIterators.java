package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is iterator of iterators.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class IteratorOfIterators {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            /**Current iterator.*/
            Iterator<Integer> iterator = it.next();
            /**The next element in the iteration.*/
            Integer next = null;

            /**
             * Returns {@code true} if the iteration has more elements.
             * @return {@code true} if the iteration has more elements.
             */
            @Override
            public boolean hasNext() {
                boolean hasNext = false;
                if (next == null) {
                    while (next == null && iterator.hasNext() || next == null && it.hasNext()) {
                        if (iterator.hasNext()) {
                            next = iterator.next();
                            hasNext = true;
                        } else if (it.hasNext()) {
                            iterator = it.next();
                        }
                    }
                } else {
                    hasNext = true;
                }
                return hasNext;
            }

            /**
             * Returns the next element in the iteration.
             * @return the next element in the iteration.
             * @throws NoSuchElementException if the iteration has no more elements.
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer result = next;
                next = null;
                return result;
            }
        };
    }
}
