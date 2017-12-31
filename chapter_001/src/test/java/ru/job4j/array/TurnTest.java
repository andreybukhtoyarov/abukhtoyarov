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
public class TurnTest {
	@Test
	public void whenArrayFourOneSevenTwoThenTwoSevenOneFour() {
		Turn turn = new Turn();
		int[] array = {4, 1, 7, 2};

		int[] result = turn.back(array);
		int[] expect = {2, 7, 1, 4};

		assertThat(result,
				is(expect)
		);
	}
	
	@Test
	public void whenArrayFourOneSevenThenSevenOneFour() {
		Turn turn = new Turn();
		int[] array = {4, 1, 7};

		int[] result = turn.back(array);
		int[] expect = {7, 1, 4};

		assertThat(
				result,
				is(expect)
		);
	}
}
