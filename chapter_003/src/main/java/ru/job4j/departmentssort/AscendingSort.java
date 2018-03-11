package ru.job4j.departmentssort;

/**
 * implementation of the interface in ascending order.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class AscendingSort implements Compare {
    @Override
    public int comparing(String o1, String o2, int index, String splitter) {
        return o1.split(splitter)[index].compareTo(o2.split(splitter)[index]);
    }
}
