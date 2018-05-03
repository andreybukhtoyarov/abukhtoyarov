package ru.job4j.tree;

import java.util.*;

/**
 * Binary Search Tree.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {
    /**
     * Link on root of tree.
     */
    private Node<E> root;
    /**
     * Size of tree.
     */
    private int size = 0;
    /**
     * Modification counter.
     */
    private int modCount = 0;

    private Node<E> currentNode;

    /**
     * Add element to the tree.
     * @param element element
     * @return {@code true} if element added.
     */
    public boolean add(E element) {
        int expectedSize = size;
        if (this.root == null && element != null) {
            this.root = new Node<>(element);
            incrementer();
        } else if (element != null) {
            if (element.compareTo(currentNode.element) <= 0) {
                if (currentNode.left != null) {
                    currentNode = currentNode.left;
                    add(element);
                } else {
                    currentNode.left = new Node<>(element);
                    incrementer();
                }
            } else if (element.compareTo(currentNode.element) > 0) {
                if (currentNode.right != null) {
                    currentNode = currentNode.right;
                    add(element);
                } else {
                    currentNode.right = new Node<>(element);
                    incrementer();
                }
            }
        }
        return expectedSize != size;
    }

    private void incrementer() {
        size++;
        modCount++;
        currentNode = root;
    }

    /**
     * Get size of tree.
     * @return size of tree.
     */
    public int getSize() {
        return size;
    }

    /**
     * Iterator for Binary Search Tree.
     */
    private class TreeIterator implements Iterator<E> {
        /**
         * Expected count of modification.
         */
        int expectedModCount = modCount;
        /**
         * Queue of elements from tree.
         */
        Queue<Node<E>> data = new LinkedList<>();

        private TreeIterator() {
            execute();
        }

        /**
         * Fill queue of elements.
         */
        private void execute() {
            if (root != null) {
                Queue<Node<E>> nodes = new LinkedList<>();
                nodes.offer(root);
                while (!nodes.isEmpty()) {
                    data.offer(nodes.peek());
                    Node<E> node = nodes.poll();
                    assert node != null;
                    if (node.left != null) {
                        nodes.offer(node.left);
                    }
                    if (node.right != null) {
                        nodes.offer(node.right);
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return !data.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data.poll().element;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator();
    }

    private class Node<T> {
        private Node<T> left;
        private Node<T> right;
        private T element;

        private Node(T element) {
            this.element = element;
        }
    }
}
