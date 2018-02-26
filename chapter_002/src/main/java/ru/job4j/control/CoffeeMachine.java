package ru.job4j.control;

/**
 * This class is a program of giving out change by a coffee machine.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class CoffeeMachine {
    /**
     * This method return array of coins.
     * @param value - received money.
     * @param price - price.
     * @return - change.
     */
    int[] changes(int value, int price) {
        int[] change = null;
        if (value - price > 0) {
            int coin = 10;
            int difference = value - price;
            int changeLength = 0;
            while (difference > 0) {
                if (difference >= coin) {
                    ++changeLength;
                    difference -= coin;
                } else {
                    coin /= 2;
                }
            }
            coin = 10;
            difference = value - price;
            change = new int[changeLength];
            int index = 0;
            while (difference > 0) {
                if (difference >= coin) {
                    difference -= coin;
                    change[index] = coin;
                    ++index;
                } else {
                    coin /= 2;
                }
            }
        }
        return change;
    }
}
