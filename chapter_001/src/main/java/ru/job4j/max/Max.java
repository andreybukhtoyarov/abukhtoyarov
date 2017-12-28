package ru.job4j.max;

/**
 * This class contains a method that returns the maximum number.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @since 27.12.2017.
 * @version 1.0.
 */
public class Max {
    /**
     *This method return the maximum number.
     * @param first - first number.
     * @param second - second number.
     * @return the maximum number of the first and second.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}
