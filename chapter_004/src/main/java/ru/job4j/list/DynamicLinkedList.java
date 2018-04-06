package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is simple Linked List.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class DynamicLinkedList<E> implements SimpleContainer<E> {
    /**
     * Size of list.
     */
    private int size = 0;

    /**
     * Count of modify in data.
     */
    private int modCount = 0;

    /**
     * Link on first element in list.
     */
    private Node<E> first;

    /**
     * Link on last element in list.
     */
    private Node<E> last;

    /**
     * Return new iterator for list.
     * @return new iterator.
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int expectedModCount = modCount;
            int cursor = 0;

            private void checkModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkModification();
                return this.cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(cursor++);
            }
        };
    }

    /**
     * Return node by index.
     * @param index index of node.
     * @return node.
     */
    private Node<E> getNode(int index) {
        checkIndex(index);
        Node<E> node;
        if (index == this.size / 2) {
            node = this.first;
            for (int position = 0; position < index; ++position) {
                node = node.next;
            }
        } else {
            node = this.last;
            for (int position = this.size - 1; position > index; --position) {
                node = node.prev;
            }
        }
        return node;
    }

    /**
     * Added new element in collection.
     * @param value new element.
     * @return added element.
     */
    @Override
    public E add(E value) {
        Node<E> newElement = new Node<>(this.last, value, null);
        if (this.last == null) {
            this.first = newElement;
            this.last = newElement;
        } else {
            this.last.next = newElement;
            this.last = newElement;
        }
        this.size++;
        this.modCount++;
        return value;
    }

    /**
     * Delete element from collection.
     * @param index index of element.
     */
    public void delete(int index) {
        Node<E> deleteNode = getNode(index);
        if (this.first == this.last) {
            this.first = null;
            this.last = null;
            this.modCount++;
            this.size--;
        }
        if (deleteNode.prev != null) {
            deleteNode.prev.next = deleteNode.next;
            if (deleteNode.next == null) {
                this.last = deleteNode.prev;
            }
            this.modCount++;
            this.size--;
        }
        if (deleteNode.next != null) {
            deleteNode.next.prev = deleteNode.prev;
            if (deleteNode.prev == null) {
                this.first = deleteNode.next;
            }
            this.modCount++;
            this.size--;
        }
    }

    /**
     * Remove first element from collection.
     */
    public void removeFirst() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }
        if (this.first.next == null) {
            this.first = null;
            this.last = null;
            this.modCount++;
            this.size--;
        } else {
            this.first = this.first.next;
            this.modCount++;
            this.size--;
        }
    }

    /**
     * Remove last element from collection.
     */
    public void removeLast() {
        if (this.last == null) {
            throw new NoSuchElementException();
        }
        if (this.size == 1) {
            this.first = null;
            this.last = null;
            this.modCount++;
            this.size--;
        } else {
            this.last = this.last.prev;
            this.modCount++;
            this.size--;
        }
    }

    /**
     * Get first element from list.
     * @return first element from list.
     */
    public E getFirst() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }
        return this.first.element;
    }

    /**
     * Get last element from list.
     * @return last element from list.
     */
    public E getLast() {
        if (this.last == null) {
            throw new NoSuchElementException();
        }
        return this.last.element;
    }

    /**
     * Get size of list.
     * @return size of list.
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks if the given index is in range. If not, throws an appropriate runtime exception.
     * @param index number to check.
     */
    private void checkIndex(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Get element from collection.
     * @param index index of element.
     * @return element.
     */
    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    /**
     * This class is wrapper on element from collection.
     * @param <E>
     */
    private static class Node<E> {
        /**
         * Stored element.
         */
        E element;
        /**
         * Link on previous node.
         */
        Node<E> prev;
        /**
         * Link on next node.
         */
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }
}
