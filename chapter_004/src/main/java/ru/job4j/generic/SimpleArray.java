package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is simple wrapper on array..
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * Array of elements.
     */
    private Object[] values;
    /**
     * Size of array.
     */
    private int size = 0;

    /**
     * Constructor with capacity.
     * @param capacity is capacity of array.
     */
    public SimpleArray(int capacity) {
        this.values = new Object[capacity];
    }

    /**
     * Add new element on array.
     * @param model new element.
     */
    public void add(T model) {
        if (this.size >= this.values.length * 0.75d) {
            this.values = Arrays.copyOf(this.values, (int) (this.size + this.size * 0.75d));
        }
        this.values[this.size++] = model;
    }

    /**
     * Replaces the element at the specified position in this array.
     * @param index index of the element to replace.
     * @param model element to be stored at the specified position
     */
    public void set(int index, T model) {
        checkSize(index);
        this.values[index] = model;
    }

    /**
     * Delete the element at the specified position.
     * @param index index of the element to delete.
     */
    public void delete(int index) {
        checkSize(index);
        Object[] tmp = new Object[this.values.length];
        System.arraycopy(this.values, 0, tmp, 0, index);
        System.arraycopy(this.values, index + 1, tmp, index, this.size);
        this.values = Arrays.copyOf(tmp, tmp.length);
        --this.size;
    }

    /**
     * Get the element at the specified position.
     * @param index index of the element to get.
     * @return element at the specified position.
     */
    public T get(int index) {
        checkSize(index);
        return (T) this.values[index];
    }

    /**
     * Get size of array.
     * @return size of array.
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks if the given index is in range.  If not, throws an appropriate runtime exception.
     * @param index number to check.
     */
    private void checkSize(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns a iterator over the elements in this array.
     * @return iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return size != cursor;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) values[cursor++];
            }
        };
    }

}
