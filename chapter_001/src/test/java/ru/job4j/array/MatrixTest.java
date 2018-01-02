package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 *
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class MatrixTest {
	@Test
	public void whenMultipleSizeThreeThenMatrixMultiplicationToThree() {
		Matrix matrix = new Matrix();

		int[][] expected = {{1, 2, 3}, {2, 4, 6}, {3, 6, 9}};
		int[][] result = matrix.multiple(3);

		assertThat(
				result,
				is(expected)
		);
	}

	@Test
	public void whenMultipleSizeTwoThenMatrixMultiplicationToTwo() {
		Matrix matrix = new Matrix();

		int[][] expected = {{1, 2}, {2, 4}};
		int[][] result = matrix.multiple(2);

		assertThat(
				result,
				is(expected)
		);
	}
}
