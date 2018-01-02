package ru.job4j.array;

/**
 * This class contain method which sorts arrays.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class BubbleSort {
	/**
	 * This method sorts ascending the array using the bubble method.
	 *@param array - array for sorting.
	 *@return sorted array.
	 */
	public int[] sort(int[] array) {
		for (int iteration = 0; iteration < array.length; ++iteration) {
			for (int index = 0; index < array.length - 1; ++index) {
				if (array[index] > array[index + 1]) {
					int tmp = array[index + 1];
					array[index + 1] = array[index];
					array[index] = tmp;
				}
			}
		}
		return array;
	}
}
