package ru.job4j.array;

/**
 *This class contain method, which inverts the integer array.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class Turn {
	/**
	 *This method  invrets the integer array.
	 *@param array - the array.
	 *@return inverted array.
	 */
	public int[] back(int[] array) {
		for (int index = 0; index < array.length / 2; ++index) {
			int tmp = array[array.length - 1 - index];
			array[array.length - 1 - index] = array[index];
			array[index] = tmp;
		}
		return array;
	}
}
