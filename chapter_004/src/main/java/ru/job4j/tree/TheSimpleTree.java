package ru.job4j.tree;

import java.util.*;

/**
 * The simple tree.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class TheSimpleTree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Link on root of tree.
     */
    private Node<E> root;
    /**
     * Modification counter.
     */
    private int countMod = 0;

    public TheSimpleTree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return {@code true} if element added.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean added = false;
        Optional<Node<E>> parentOpt = findBy(parent);
        Optional<Node<E>> childOpt = findBy(child);
        if (!childOpt.isPresent() && parentOpt.isPresent()) {
            parentOpt.get().add(new Node<>(child));
            this.countMod++;
            added = true;
        } else if (!childOpt.isPresent()) {
            this.root.add(new Node<>(parent));
            findBy(parent).get().add(new Node<>(child));
            this.countMod++;
            added = true;
        }
        return added;
    }

    /**
     * Find Node by value.
     * @param value current value.
     * @return Node<E></>.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Check if the tree is binary.
     * @return {@code true} if tree is binary.
     */
    public boolean isBinary() {
        boolean is = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> node = data.poll();
            if (node.leaves().size() > 2) {
                is = false;
                break;
            }
            for (Node<E> n : node.leaves()) {
                data.offer(n);
            }
        }
        return is;
    }

    /**
     * Iterator for tree.
     */
    class TreeIterator implements Iterator<E> {
        /**
         * Expected count of modification.
         */
        private int expectedCountMod = countMod;
        /**
         * Queue of elements from tree.
         */
        private Queue<E> elements = new LinkedList<>();

        /**
         * Fill queue of elements.
         */
        private void fill() {
            Queue<Node<E>> data = new LinkedList<>();
            data.offer(root);
            while (!data.isEmpty()) {
                Node<E> node = data.poll();
                elements.offer(node.getValue());
                for (Node<E> n : node.leaves()) {
                    data.offer(n);
                }
            }
        }

        public TreeIterator() {
            fill();
        }

        @Override
        public boolean hasNext() {
            if (expectedCountMod != countMod) {
                throw new ConcurrentModificationException();
            }
            return !elements.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements.poll();
        }
    }

    /**
     * Return new iterator.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator();
    }
}
