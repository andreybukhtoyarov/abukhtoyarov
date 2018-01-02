package ru.job4j.array;

/**
 * This class contain method which returns matrix multiplication table.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class Matrix {
	/**
	 *This method returns matrix multiplication table.
	 *@param size - the size of matrix.
	 *@return matrix multiplication table.
	 */
	int[][] multiple(int size) {
		int[][] matrix = new int[size][size];

		for (int line = 0; line < size; ++line) {
			for (int column = 0; column < size; ++column) {
				matrix[line][column] = (line + 1) * (column + 1);
			}
		}
		return matrix;
	}
}
