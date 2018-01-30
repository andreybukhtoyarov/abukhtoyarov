package ru.job4j.control;

/**
 * This class contain general methods for vending machines.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public abstract class Consider implements Change {
	/**
	 * This method counts the number of coins of a certain denomination.
	 * @param value - received money.
	 * @param price - price.
	 * @param coin - array of coins.
	 */
	public void countingCoins(int value, int price, int[][] coin) {
		int difference = value - price;
		for (int index = 0; index < coin.length; ++index) {
			coin[index][1] = difference / coin[index][0];
			difference = difference - coin[index][0] * coin[index][1];
		}
	}
	/**
	 * This method return array of coins for change.
	 * @param coin - array of coins.
	 * @return - change.
	 */
	public int[] fillingCoins(int[][] coin) {
		int changeLength = 0;
		for (int index = 0; index < coin.length; ++index) {
			changeLength = changeLength + coin[index][1];
		}
		int[] change = new int[changeLength];
		int indexChange = 0;
		for (int index = 0; index < coin.length; ++index) {
			if (coin[index][1] > 0) {
				for (int indexTwo = 0; indexTwo < coin[index][1]; ++indexTwo) {
					change[indexChange] = coin[index][0];
					++indexChange;
				}
			}
		}
		return change;
	}
}
