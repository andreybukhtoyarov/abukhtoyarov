package ru.job4j.set;

import java.util.Arrays;

/**
 * This class is simple Hash table.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SimpleHashTable<E> {
    /**
     * Array with data.
     */
    private Object[] hashTable;
    /**
     * Count of elements.
     */
    private int size = 0;
    /**
     * Standard capacity.
     */
    private int stdCapacity = 100;

    private int threshold = 75;

    /**
     * Constructor.
     */
    public SimpleHashTable() {
        this.hashTable = new Object[stdCapacity];
    }

    /**
     * Make index by hash.
     * @param e element.
     * @return unique index.
     */
    private int hashIndex(E e) {
        return Math.abs(e.hashCode() % hashTable.length);
    }

    /**
     * Increases the capacity of and internally reorganizes this hashTable.
     * @return true if hashTable is rehashed.
     */
    private boolean reHash() {
        boolean rehash = false;
        Object[] oldHashTable = this.hashTable;
        int newCapacity = increaseCapacity();
        if (newCapacity != this.hashTable.length) {
            this.hashTable = new Object[newCapacity];
            this.threshold = (int) (this.hashTable.length * 0.75f);
            for (Object element : oldHashTable) {
                if (element != null) {
                    this.hashTable[hashIndex((E) element)] = element;
                }
            }
            rehash = true;
        }
        return rehash;
    }

    /**
     * Increase capacity of hashTable.
     * @return new capacity of hashTable.
     */
    private int increaseCapacity() {
        int newCapacity = this.hashTable.length << 1;
        if (newCapacity > Integer.MAX_VALUE - 8) {
            newCapacity = Integer.MAX_VALUE - 8;
        }
        return newCapacity;
    }

    /**
     * Add element to hashTable.
     * @param e element.
     * @return true if element added.
     */
    public boolean add(E e) {
        boolean added = false;
        if (e != null) {
            int index = hashIndex(e);
            if (index >= threshold) {
                if (reHash()) {
                    int newIndex = hashIndex(e);
                    hashTable[newIndex] = e;
                    added = true;
                    size++;
                }
            } else {
                hashTable[index] = e;
                added = true;
                size++;
            }

        }
        return added;
    }

    /**
     * Remove element from hashTable.
     * @param e element.
     * @return true if element deleted.
     */
    public boolean remove(E e) {
        boolean removed = false;
        if (e != null) {
            int index = hashIndex(e);
            if (index < hashTable.length && hashTable[index] != null) {
                hashTable[index] = null;
                removed = true;
                size--;
            }
        }
        return removed;
    }

    /**
     * Return true if hashTable contains this element.
     * @param e element.
     * @return true if hashTable contains this element.
     */
    public boolean contains(E e) {
        boolean contains = false;
        if (e != null) {
            int index = hashIndex(e);
            if (index < hashTable.length && hashTable[index] != null && hashTable[index].equals(e)) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * Return count of element in hashTable.
     * @return count of element in hashTable.
     */
    public int size() {
        return size;
    }
}
