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
            /*
             * left column - denomination of a coin.
             * right column - count of coins.
             */
            int[][] coin = new int[][] {
                    {10, 0},
                    {5, 0},
                    {2, 0},
                    {1, 0}
            };

            int difference = value - price;
            for (int index = 0; index < coin.length; ++index) {
                coin[index][1] = difference / coin[index][0];
                difference = difference - coin[index][0] * coin[index][1];
            }

            int changeLength = 0;
            for (int index = 0; index < coin.length; ++index) {
                changeLength = changeLength + coin[index][1];
            }
            change = new int[changeLength];
            int indexChange = 0;
            for (int index = 0; index < coin.length; ++index) {
                if (coin[index][1] > 0) {
                    for (int indexTwo = 0; indexTwo < coin[index][1]; ++indexTwo) {
                        change[indexChange] = coin[index][0];
                        ++indexChange;
                    }
                }
            }
        }
        return change;
    }
}
