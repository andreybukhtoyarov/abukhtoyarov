package ru.job4j.tree;

import java.util.Optional;

/**
 * This interface for simple trees.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return {@code true} if element added.
     */
    boolean add(E parent, E child);

    /**
     * Find Node by value.
     * @param value current value.
     * @return Node<E></>.
     */
    Optional<Node<E>> findBy(E value);
}
