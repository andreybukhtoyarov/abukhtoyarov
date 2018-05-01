package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is node for tree.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Node<E extends Comparable<E>> {
    /**
     * List of children.
     */
    private final List<Node<E>> children = new ArrayList<>();
    /**
     * Stored element.
     */
    private final E value;

    /**
     * Constructor.
     * @param value element of data.
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Add new Node to children list.
     * @param child Node child.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Return list of children.
     * @return list of children.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Compare values.
     * @param that value to compare.
     * @return {@code true} if values is equals.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;

    }

    /**
     * Get value.
     * @return this value.
     */
    public E getValue() {
        return this.value;
    }
}
