package ru.job4j.set;

import ru.job4j.list.DynamicLinkedList;
import java.util.Iterator;

/**
 * This class is simple Set based on Linked List from my class DynamicLinkedList.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SimpleSetOnLinkedList<E> implements Iterable {
    /**
     * Data storage.
     */
    private DynamicLinkedList<E> data;

    /**
     * Void constructor.
     * Create new DynamicList with capacity 16.
     */
    public SimpleSetOnLinkedList() {
        data = new DynamicLinkedList<>();
    }

    /**
     * Return size of set.
     * @return size of set.
     */
    public int size() {
        return data.size();
    }

    /**
     * Checks the element for uniqueness.
     * @param e element for check.
     * @return true if element is unique.
     */
    private boolean unique(E e) {
        boolean unique = true;
        for (E element : data) {
            if (e.equals(element)) {
                unique = false;
                break;
            }
        }
        return unique;
    }

    /**
     * Add element to collection.
     * @param e element.
     */
    public void add(E e) {
        if (unique(e)) {
            data.add(e);
        }
    }

    /**
     * Return iterator for Set.
     * @return iterator for Set.
     */
    @Override
    public Iterator iterator() {
        return data.iterator();
    }
}
