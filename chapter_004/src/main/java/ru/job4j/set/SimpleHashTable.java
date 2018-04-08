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

    /**
     * Constructor.
     */
    public SimpleHashTable() {
        this.hashTable = new Object[stdCapacity];
    }

    /**
     * Make index by hash.
     * @param e element.
     * @return unique.
     */
    private int hashIndex(E e) {
        return e.hashCode() % hashTable.length;
    }

    /**
     * Increase capacity of hashTable.
     * @return new capacity of hashTable.
     */
    private int increased() {
        return hashTable.length + (int) (hashTable.length * 0.75d);
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
            while (hashTable.length <= index) {
                hashTable = Arrays.copyOf(hashTable, increased());
            }
            hashTable[index] = e;
            added = true;
            size++;
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
