package ru.job4j.control;

/**
 * This class is a program of giving out change by a coffee machine.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class CoffeeMachineSurrender extends Consider {
	/**
	 * This method return array of coins.
	 * @param value - received money.
	 * @param price - price.
	 * @return - change.
	 */
	public int[] changes(int value, int price) {
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
			countingCoins(value, price, coin);
			change = fillingCoins(coin);
		}
		return change;
	}
}
