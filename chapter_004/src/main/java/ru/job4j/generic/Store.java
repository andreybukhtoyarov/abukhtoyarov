package ru.job4j.generic;

/**
 * Interface for storage classes which extend class Base .
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface Store<T extends Base> {
    /**
     * Add element to store.
     * @param model element to store.
     */
    void add(T model);

    /**
     * Replace element from store.
     * @param id id of element.
     * @param model new element.
     * @return {@code true} if elements is replaced.
     */
    boolean replace(String id, T model);

    /**
     * Delete element from store.
     * @param id id of element.
     * @return {@code true} if elements is deleted.
     */
    boolean delete(String id);

    /**
     * Return element from store.
     * @param id id of element.
     * @return element from store.
     */
    T findById(String id);
}
