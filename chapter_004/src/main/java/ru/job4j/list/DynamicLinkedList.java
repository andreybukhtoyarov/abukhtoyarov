package ru.job4j.list;

import java.util.Iterator;
import java.util.LinkedList;

public class DynamicLinkedList<E> implements SimpleContainer<E> {

    private int size = 0;

    private int modCount = 0;

    private Node<E> first;

    private Node<E> last;

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }

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

    public E getFirst() {
        return this.first.element;
    }

    public E getLast() {
        return this.last.element;
    }

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

    @Override
    public E get(int index) {
        checkIndex(index);
        E element;
        if (index == this.size / 2) {
            Node<E> node = this.first;
            for (int position = 0; position < index; ++position) {
                node = node.next;
            }
            element = node.element;
        } else {
            Node<E> node = this.last;
            for (int position = this.size - 1; position > index; --position) {
                node = node.prev;
            }
            element = node.element;
        }
        return element;
    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }
}
