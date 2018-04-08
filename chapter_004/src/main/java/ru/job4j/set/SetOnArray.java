package ru.job4j.set;

import ru.job4j.list.DynamicList;
import java.util.Iterator;

/**
 * This class is simple Set based on Array from my class DynamicList.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SetOnArray<E> implements Iterable {
    private DynamicList<E> data;

    /**
     * Void constructor.
     * Create new DynamicList with capacity 16.
     */
    public SetOnArray() {
        data = new DynamicList<>();
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
     * @param element element for check.
     * @return true if element is unique.
     */
    private boolean unique(E element) {
        boolean unique = true;
        for (E e : data) {
            if (e.equals(element)) {
                unique = false;
                break;
            }
        }
        return unique;
    }

    /**
     * Add element to collection.
     * @param element element.
     */
    public void add(E element) {
        if (unique(element)) {
            data.add(element);
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
