package ru.job4j.list;

public interface SimpleContainer<E> extends Iterable<E> {
    E add(E value);
    E get(int index);
}
