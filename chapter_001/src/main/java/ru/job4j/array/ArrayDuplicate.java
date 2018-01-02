package ru.job4j.array;

import java.util.Arrays;

/**
 * This class contain method which deleted duplikates words in array.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class ArrayDuplicate {
	/**
	 * This method deleted duplikates words in array.
	 *@param array - array with duplicates words.
	 *@return array without duplicates  words.
	 */
	public String[] remove(String[] array) {
		int unique = array.length;

		for (int iteration = 0; iteration < unique; ++iteration) {
			for (int index = iteration + 1; index < unique; ++index) {
				if (array[iteration].equals(array[index])) {
					array[index] = array[unique - 1];
					--unique;
					--index;
				}
			}
		}
		return Arrays.copyOf(array, unique);
	}
}
