package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is simple dynamic wrapper on array.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class DynamicList<E> implements Iterable<E> {
    /**
     * Array of elements.
     */
    private Object[] data;

    /**
     * Size of array.
     */
    private int size = 0;

    /**
     * Count of modify in data.
     */
    private int modCount = 0;

    public DynamicList(int capacity) {
        this.data = new Object[capacity];
    }

    public DynamicList(Object[] data) {
        this.data = data;
    }

    public DynamicList() {
        this.data = new Object[16];
    }

    /**
     * Add new element in data.
     * @param value new element.
     * @return added element.
     */
    public E add(E value) {
        if (this.size >= this.data.length * 0.75d) {
            this.data = Arrays.copyOf(this.data, (int) (this.size + this.size * 0.75d));
        }
        this.data[size++] = value;
        ++this.modCount;
        return value;
    }

    /**
     * Get the element at the specified position.
     * @param index index of the element to get.
     * @return element at the specified position.
     */
    public E get(int index) {
        checkIndex(index);
        return (E) this.data[index];
    }

    /**
     * Checks if the given index is in range.  If not, throws an appropriate runtime exception.
     * @param index number to check.
     */
    private void checkIndex(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Return size of data.
     * @return size of data.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns a iterator over the elements in this data.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Position of iterator.
             */
            int cursor = 0;
            /**
             * Storage of modify.
             */
            int expectedModCount = modCount;

            /**
             * @throws ConcurrentModificationException if the data was modified.
             */
            private void checkModification() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkModification();
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) data[cursor];
            }
        };
    }
}
