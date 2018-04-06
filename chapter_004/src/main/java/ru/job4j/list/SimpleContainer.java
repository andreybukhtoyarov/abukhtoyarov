package ru.job4j.list;

/**
 * This class is interface for simple container classes.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Added new element in collection.
     * @param value new element.
     * @return added element.
     */
    E add(E value);

    /**
     * Get element from collection.
     * @param index index of element.
     * @return element.
     */
    E get(int index);
}
