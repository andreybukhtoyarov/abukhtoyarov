package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com)..
 *@version %Id%.
 *@since 0.1.
 */
public class SquareTest {
	@Test
	public void whenCalculateBoundEqualsFiveThen() {
		Square square = new Square();
		int[] result = square.calculate(5);
		int[] expected = {1, 4, 9, 16, 25};
		assertThat(
				result,
				is(expected)
		);
	}
}
	