package ru.job4j.control;

/**
 * This class is interface for calculating the change in vending machines.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface Change {
    /**
     * This method return array of coins.
     * @param value - received money.
     * @param price - price.
     * @return - change.
     */
    int[] changes(int value, int price);
}
