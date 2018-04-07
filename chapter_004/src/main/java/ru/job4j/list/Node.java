package ru.job4j.list;

/**
 * This class is wrapper on element from collection.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Node<T> {
    /**
     * Stored element.
     */
    private T element;
    /**
     * Link to next node.
     */
    Node<T> next;

    /**
     * Set link to next node.
     * @param next link to the next node.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node(T element, Node<T> next) {
        this.element = element;
        this.next = next;
    }
}
