package ru.job4j.departmentssort;

/**
 * Interface with method for comparing.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface Compare {
    /**
     * String comparator.
     * @param o1 - line for compare #1.
     * @param o2 - line for compare #2.
     * @param index - index of line.
     * @param splitter - splitter.
     * @return 1 if the first is greater, 0 if the wound and -1 if the first is less.
     */
    int comparing(String o1, String o2, int index, String splitter);
}
