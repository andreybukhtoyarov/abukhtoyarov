package ru.job4j.set;

/**
 * This class is simple Hash Set based on simple Hash table.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SetOnHashTable<E> {
    /**
     * Storage.
     */
    private SimpleHashTable<E> hashTable;

    /**
     * Constructor.
     */
    public SetOnHashTable() {
        this.hashTable = new SimpleHashTable<>();
    }

    /**
     * Add element to Set.
     * @param e element.
     * @return true if element added.
     */
    public boolean add(E e) {
        boolean added = false;
        if (!hashTable.contains(e)) {
            added = hashTable.add(e);
        }
        return added;
    }

    /**
     * Remove element from Set.
     * @param e element.
     * @return true if element deleted.
     */
    public boolean remove(E e) {
        return hashTable.remove(e);
    }

    /**
     * Return true if Set contains this element.
     * @param e element.
     * @return true if Set contains this element.
     */
    public boolean contains(E e) {
        return hashTable.contains(e);
    }

    /**
     * Return count of element in Set.
     * @return count of element in Set.
     */
    public int size() {
        return hashTable.size();
    }
}
