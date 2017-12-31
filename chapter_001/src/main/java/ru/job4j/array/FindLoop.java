package ru.job4j.array;

/**
 *This class contain method, which returns the index of an array element.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class FindLoop {
	/**
	 *This method returns the index of an array element.
	 *@param data - the array.
	 *@param value - element of array.
	 *@return the index of value.
	 */
	public int indexOf(int[] data, int value) {
		int indexValue = -1;
		for (int index = 0; index < data.length; ++index) {
			if (data[index] == value) {
				indexValue = index;
				break;
			}
		}
		return indexValue;
	}
}
