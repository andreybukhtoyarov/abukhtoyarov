package ru.job4j.array;

/**
 *This class contain method, which return array of integers from 1 to bound.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class Square {
	/**
	 *This method return array of integers from 1 to bound.
	 *@param bound - the size of the array.
	 *@return integer array.
	 */
	public int[] calculate(int bound) {
		int[] result = new int[bound];
		
		for (int count = 1; count - 1 < bound; ++count) {
			result[count - 1] = (int) Math.pow(count, 2);
		}
		return result;
	}
}
