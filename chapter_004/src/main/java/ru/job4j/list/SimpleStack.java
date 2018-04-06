package ru.job4j.list;

/**
 * This class is simple Stack.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SimpleStack<T> {
    /**
     * List of elements.
     */
    private final DynamicLinkedList<T> elements = new DynamicLinkedList<>();

    /**
     * Size of Stack.
     * @return size of Stack.
     */
    public int size() {
        return this.elements.size();
    }

    /**
     * Return last element from Stack.
     * @return last element from Stack.
     */
    public T poll() {
        T element = elements.getLast();
        elements.removeLast();
        return element;
    }

    /**
     * Add new element to Stack.
     * @param value new element.
     */
    public void push(T value) {
        elements.add(value);
    }
}
