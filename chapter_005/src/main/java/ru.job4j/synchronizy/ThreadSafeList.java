package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicList;

import java.util.Iterator;

/**
 * Thread Safe Simple Array List.
 * @param <E> Element.
 */
@ThreadSafe
public class ThreadSafeList<E> implements Iterable<E> {
    /**
     * DynamicList.
     */
    @GuardedBy("this")
    private final DynamicList<E> dm;

    public ThreadSafeList() {
        this.dm = new DynamicList<>();
    }

    /**
     * Return size of DynamicList.
     * @return size of DynamicList.
     */
    public synchronized int size() {
        return dm.size();
    }

    /**
     * Add new element to DynamicList.
     * @param value new element.
     * @return added element.
     */
    public synchronized E add(E value) {
        return dm.add(value);
    }

    /**
     * Get element from DynamicList.
     * @param index index of element.
     * @return element.
     */
    public synchronized E get(int index) {
        return dm.get(index);
    }

    /**
     * Return copy of DynamicList.
     * @return copy of this DynamicList.
     */
    private DynamicList<E> copy() {
        DynamicList<E> copy = new DynamicList<>();
        for (E e : this.dm) {
            copy.add(e);
        }
        return copy;
    }

    /**
     * Return fail-safe Iterator of DynamicList.
     * @return fail-safe Iterator of DynamicList.
     */
    @Override
    public synchronized Iterator<E> iterator() {
        return copy().iterator();
    }
}
