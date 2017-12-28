package ru.job4j.loop;

/**
 * This class contains method add.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @since 28.12.2017.
 * @version 1.0.
 */
public class Counter {
    /**
     * This method calculate sum of all numbers of multiple 2 in range between start and finish..
     * @param start - start number of range.
     * @param finish - finish number of range.
     * @return sum of all numbers of multiple 2.
     */
    public int add(int start, int finish) {
        int result = 0;

        for (int i = start; i <= finish; ++i) {
            if (i % 2 == 0) {
                result = result + i;
            }
        }

        return result;
    }
}
