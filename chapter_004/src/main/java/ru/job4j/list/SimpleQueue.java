package ru.job4j.list;

/**
 * This class is simple Queue.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SimpleQueue<T> {
    /**
     * List of elements.
     */
    private final DynamicLinkedList<T> elements = new DynamicLinkedList<>();

    /**
     * Size of Queue.
     * @return size of Queue.
     */
    public int size() {
        return this.elements.size();
    }

    /**
     * Return first element from Queue.
     * @return first element from Queue.
     */
    public T poll() {
        T element = elements.getFirst();
        elements.removeFirst();
        return element;
    }

    /**
     * Add new element to Queue.
     * @param value new element.
     */
    public void push(T value) {
        elements.add(value);
    }
}
