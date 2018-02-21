package ru.job4j.control;

/**
 * This class is a program of giving out change by a coffee machine.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class CoffeeMachine {
    /**Left column - denomination of a coins, right column - count of coins. */
    private final int[][] coins = new int[][] {
            {10, 0},
            {5, 0},
            {2, 0},
            {1, 0}
    };

    /**
     * This method return array of coins.
     * @param value - received money.
     * @param price - price.
     * @return - change.
     */
    int[] changes(int value, int price) {
        int[] change = null;
        if (value - price > 0) {
            int difference = value - price;
            for (int index = 0; index < coins.length; ++index) {
                coins[index][1] = difference / coins[index][0];
                difference = difference - coins[index][0] * coins[index][1];
            }
            int changeLength = 0;
            for (int[] coin : coins) {
                changeLength = changeLength + coin[1];
            }
            change = new int[changeLength];
            int indexChange = 0;
            for (int[] coin : coins) {
                if (coin[1] > 0) {
                    for (int indexTwo = 0; indexTwo < coin[1]; ++indexTwo) {
                        change[indexChange] = coin[0];
                        ++indexChange;
                    }
                }
            }
        }
        return change;
    }
}
